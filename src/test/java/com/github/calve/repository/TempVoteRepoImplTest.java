package com.github.calve.repository;

import com.github.calve.model.Menu;
import com.github.calve.model.MenuItem;
import com.github.calve.model.VoteLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.github.calve.repository.VoteTestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitWebConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Transactional
@ActiveProfiles("datajpa")
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class TempVoteRepoImplTest {
    @Autowired
    private TempVoteRepo voteRepo;

    @Test
    void getDailyMenu() {
        Menu dailyMenu = voteRepo.getDailyMenu(restaurant_1.getId());
        addMenuItemsToTestMenu(MENU_1, MENU_ITEM_1, MENU_ITEM_2, MENU_ITEM_3, MENU_ITEM_4, MENU_ITEM_5);
        assertMatch(dailyMenu, MENU_1);
        assertMatch(dailyMenu.getRestaurant(), MENU_1.getRestaurant());
        assertMatch(dailyMenu.getItems(), MENU_1.getItems());
    }


    @Test
    void saveDailyMenu() {
        Menu saved = voteRepo.saveDailyMenu(dishes, testAdmin);
        addMenuItemsToTestMenu(MENU_3, MENU_ITEM_6, MENU_ITEM_7, MENU_ITEM_8, MENU_ITEM_9);
        assertMatch(saved, MENU_3);
        assertMatch(saved.getRestaurant(), MENU_3.getRestaurant());
        assertMatch(saved.getItems(), MENU_3.getItems());
    }

    @Test
    void getVoteList() {
        List<Menu> voteList = voteRepo.getVoteList();
        addMenuItemsToTestMenu(MENU_1, MENU_ITEM_1, MENU_ITEM_2, MENU_ITEM_3, MENU_ITEM_4, MENU_ITEM_5);
        assertMatch(voteList, MENU_1, MENU_2);
        assertMatch(voteList.stream().map(Menu::getRestaurant)
                .collect(Collectors.toList()), Arrays.asList(MENU_1.getRestaurant(), MENU_2.getRestaurant()));
        assertMatch(voteList.stream().map(Menu::getItems)
                .collect(Collectors.toList()), Arrays.asList(MENU_1.getItems(), MENU_2.getItems()));
    }

    @Test
    void getVoteHistory() {
        assertMatch(voteRepo.getVoteHistory(), HISTORY_ITEM_1, HISTORY_ITEM_3, HISTORY_ITEM_2);
    }

    @Test
    void getVoteHistoryByRestaurant() {
        assertMatch(voteRepo.getVoteHistoryByRestaurant(restaurant_1.getId()), HISTORY_ITEM_1, HISTORY_ITEM_2);
    }

    @Test
    void voteUserSuccess() {
        voteRepo.vote(testUser, restaurant_1);
        List<String> list = voteRepo.getAllLogs().stream().map(VoteLog::toString).collect(Collectors.toList());
        assertTrue(list.contains(VOTE_LOG_1.toString()));
        assertEquals(voteRepo.getDailyMenu(restaurant_1.getId()).getVoteCount(), 12);
    }

    @Test
    void unlockUserVote() {
        voteRepo.unlockVote(testAdmin.getId());
        assertEquals(voteRepo.getAllLogs().size(), 1);
        assertEquals(voteRepo.getDailyMenu(restaurant_1.getId()).getVoteCount(), 10);
    }

    @Test
    void register() {
    }

    @Test
    void login() {
    }

    private void addMenuItemsToTestMenu(Menu menu, MenuItem... args) {
        Set<MenuItem> items = new HashSet<>(
                Arrays.asList(args));
        menu.setItems(items);
    }
}