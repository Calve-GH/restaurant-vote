package com.github.calve.repository;

import com.github.calve.model.*;
import com.github.calve.repository.datajpa.*;
import com.github.calve.to.DishTo;
import com.sun.istack.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.github.calve.VoteTestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitWebConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Transactional
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class VoteRepoImplTest {

    @Autowired
    private CrudVoteLogRepo voteLogRepo;
    @Autowired
    private CrudUserRepository userRepo;
    @Autowired
    private CrudMenuRepository menuRepo;
    @Autowired
    private CrudHistoryRepo historyRepo;
    @Autowired
    private CrudRestaurantRepo restaurantRepo;
    @Autowired
    private CrudDishRepository dishRepo;
    @Autowired
    private CrudMenuItemRepository menuItemRepo;

    @Value("${dish.list.min.size}")
    private Integer minDishes;
    @Value("${dish.list.max.size}")
    private Integer maxDishes;

    @Test
    void getDailyMenu() {
        Menu dailyMenu = menuRepo.getMenuByDateAndRestaurantId(LocalDate.now(), RESTAURANT_1.getId());
        addMenuItemsToTestMenu(MENU_1, MENU_ITEM_1, MENU_ITEM_2, MENU_ITEM_3, MENU_ITEM_4, MENU_ITEM_5);
        assertMatch(dailyMenu, MENU_1);
        assertMatch(dailyMenu.getRestaurant(), MENU_1.getRestaurant());
        assertMatch(dailyMenu.getItems(), MENU_1.getItems());
    }

    @Test
    void saveDailyMenu() {
        Menu saved = saveDailyMenu(dishes, TEST_ADMIN_1.getId());
        addMenuItemsToTestMenu(MENU_3, MENU_ITEM_6, MENU_ITEM_7, MENU_ITEM_8, MENU_ITEM_9);

        assertMatch(saved, MENU_3);
        assertMatch(saved.getRestaurant(), MENU_3.getRestaurant());
        assertMatch(saved.getItems(), MENU_3.getItems());
    }

    @Test
    void getVoteList() {
        List<Menu> voteList = menuRepo.findAllByDate(LocalDate.now());
        addMenuItemsToTestMenu(MENU_1, MENU_ITEM_1, MENU_ITEM_2, MENU_ITEM_3, MENU_ITEM_4, MENU_ITEM_5);
        assertMatch(voteList, Arrays.asList(MENU_1));
        assertMatch(voteList.stream().map(Menu::getRestaurant)
                .collect(Collectors.toList()), Arrays.asList(MENU_1.getRestaurant()));
        assertMatch(voteList.stream().map(Menu::getItems)
                .collect(Collectors.toList()), Arrays.asList(MENU_1.getItems()));
    }

    @Test
    void getVoteHistory() {
        List<HistoryItem> voteHistory = historyRepo.findAll();
        assertMatch(voteHistory, HISTORY_ITEM_1, HISTORY_ITEM_3, HISTORY_ITEM_2);
    }

    @Test
    void getVoteHistoryByRestaurant() {
        assertMatch(historyRepo.findByRestaurantId(RESTAURANT_1.getId()), HISTORY_ITEM_1, HISTORY_ITEM_2);
    }

    @Test
    void voteUserSuccess() {
        User user = userRepo.getOne(TEST_USER.getId());
        Restaurant restaurant = restaurantRepo.getOne(RESTAURANT_1.getId());
        VoteLog voteLog = new VoteLog(user, restaurant);
        voteLogRepo.save(voteLog);
        List<String> list = voteLogRepo.findAll().stream().map(VoteLog::toString).collect(Collectors.toList());
        System.out.println(list);
        System.out.println(VOTE_LOG_1.toString()); //t f to string exception
        assertTrue(list.contains(VOTE_LOG_1.toString())); //WRONG LDT FORMAT Сравнение по строкам
        // убрал из User.toString дату ибо из БД тянется с наносеками а там различия, пароль тоже убрал
        assertEquals(menuRepo.getMenuByDateAndRestaurantId(LocalDate.now(), RESTAURANT_1.getId()).getVoteCount(), 12);
    }

    @Test
    void unlockUserVote() {
        voteLogRepo.delete(TEST_ADMIN_1.getId());
        assertEquals(voteLogRepo.findAll().size(), 0);
        assertEquals(menuRepo.getMenuByDateAndRestaurantId(LocalDate.now(), RESTAURANT_1.getId()).getVoteCount(), 10);
    }

    public Menu saveDailyMenu(Set<DishTo> dishes, Integer adminId) {
        if (dishes.size() >= minDishes && dishes.size() <= maxDishes) {
            User admin = userRepo.findById(adminId).orElse(null);
            if (admin == null) return null;
            menuRepo.deleteByRestaurantId(admin.getRestaurant().getId());
            Menu menu = new Menu(LocalDate.now(), admin.getRestaurant());
            menu = menuRepo.save(menu);
            Set<MenuItem> items = parseMenuItems(dishes, menu);
            menu.setItems(items);
            menuRepo.save(menu);
            return menu;
        }
        return null;
    }

    @NotNull
    private Set<MenuItem> parseMenuItems(Set<DishTo> dishes, Menu menu) {
        Set<MenuItem> items = new HashSet<>();
        for (DishTo dishTo : dishes) {
            Dish dishWithId;
            if (dishTo.isNew()) {
                dishWithId = dishRepo.save(new Dish(dishTo.getName()));
            } else {
                dishWithId = dishRepo.findById(dishTo.getId()).orElse(null);
            }
            items.add(new MenuItem(menu, dishWithId, dishTo.getPrice()));
        }
        return items;
    }

    //TODO TEST
    @Test
    void testSaveOverpassMenus() {
        List<Menu> unsavedMenus = menuRepo.findByDateBefore(LocalDate.now());
        historyRepo.saveAll(JpaUtil.convertMenuListToHistoryList(unsavedMenus));
        menuRepo.deleteAll(unsavedMenus);
        voteLogRepo.deleteAllByDateBefore(LocalDate.now());
        System.out.println("----------------------------------------------------");
    }

}