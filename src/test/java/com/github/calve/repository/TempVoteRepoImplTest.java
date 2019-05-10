package com.github.calve.repository;

import com.github.calve.model.HistoryItem;
import com.github.calve.model.Menu;
import com.github.calve.model.VoteLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.calve.VoteTestData.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitWebConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Transactional
@ActiveProfiles("datajpa")
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class TempVoteRepoImplTest {
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private TempVoteRepo voteRepo;
    @Autowired
    private MenuRepository menuRepository;

    @Test
    void getDailyMenu() {
        Menu dailyMenu = menuRepository.getDailyMenu(RESTAURANT_1.getId());
        addMenuItemsToTestMenu(MENU_1, MENU_ITEM_1, MENU_ITEM_2, MENU_ITEM_3, MENU_ITEM_4, MENU_ITEM_5);
        assertMatch(dailyMenu, MENU_1);
        assertMatch(dailyMenu.getRestaurant(), MENU_1.getRestaurant());
        assertMatch(dailyMenu.getItems(), MENU_1.getItems());
    }


    @Test
    void saveDailyMenu() {
        Menu saved = menuRepository.saveDailyMenu(dishes, TEST_ADMIN_1.getId());
        addMenuItemsToTestMenu(MENU_3, MENU_ITEM_6, MENU_ITEM_7, MENU_ITEM_8, MENU_ITEM_9);
        assertMatch(saved, MENU_3);
        assertMatch(saved.getRestaurant(), MENU_3.getRestaurant());
        assertMatch(saved.getItems(), MENU_3.getItems());
    }

    @Test
    void getVoteList() {
        List<Menu> voteList = voteRepository.getVoteList();
        addMenuItemsToTestMenu(MENU_1, MENU_ITEM_1, MENU_ITEM_2, MENU_ITEM_3, MENU_ITEM_4, MENU_ITEM_5);
        assertMatch(voteList, MENU_1, MENU_2);
        assertMatch(voteList.stream().map(Menu::getRestaurant)
                .collect(Collectors.toList()), Arrays.asList(MENU_1.getRestaurant(), MENU_2.getRestaurant()));
        assertMatch(voteList.stream().map(Menu::getItems)
                .collect(Collectors.toList()), Arrays.asList(MENU_1.getItems(), MENU_2.getItems()));
    }

    @Test
    void getVoteHistory() {
        List<HistoryItem> voteHistory = voteRepository.getVoteHistory();
        voteHistory.forEach(System.out::println);
        assertMatch(voteHistory, HISTORY_ITEM_1, HISTORY_ITEM_3, HISTORY_ITEM_2);
    }

    @Test
    void getVoteHistoryByRestaurant() {
        assertMatch(voteRepository.getVoteHistoryByRestaurant(RESTAURANT_1.getId()), HISTORY_ITEM_1, HISTORY_ITEM_2);
    }

    @Test
    void voteUserSuccess() {
        voteRepository.vote(TEST_USER.getId(), RESTAURANT_1.getId());
        List<String> list = voteRepo.getAllLogs().stream().map(VoteLog::toString).collect(Collectors.toList());
        assertTrue(list.contains(VOTE_LOG_1.toString())); //TODO WRONG LDT FORMAT Сравнение по строкам
        // TODO убрал из User.toString дату ибо из БД тянется с наносеками а там различия, пароль тоже убрал
        assertEquals(menuRepository.getDailyMenu(RESTAURANT_1.getId()).getVoteCount(), 12);
    }

    @Test
    void unlockUserVote() {
        voteRepository.unlockVote(TEST_ADMIN_1.getId());
        assertEquals(voteRepo.getAllLogs().size(), 1);
        assertEquals(menuRepository.getDailyMenu(RESTAURANT_1.getId()).getVoteCount(), 10);
    }

    @Test
    void register() {
    }

    @Test
    void login() {
    }


}