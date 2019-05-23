package com.github.calve;

import com.github.calve.model.*;
import com.github.calve.to.DishTo;

import java.time.LocalDate;
import java.util.*;

import static com.github.calve.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;


public class VoteTestData {

    public static final User TEST_USER =
            new User(START_SEQ + 15, "Petrov", "petrov@gmail.com", "9876543", null, true, Collections.singletonList(Role.ROLE_USER));

    public static final Restaurant RESTAURANT_1 = new Restaurant(START_SEQ, "Sweet bobaleh");
    public static final Restaurant RESTAURANT_2 = new Restaurant(START_SEQ + 1, "ITAKA");
    public static final Restaurant RESTAURANT_3 = new Restaurant(START_SEQ + 2, "Hunter Village");

    public static final User TEST_ADMIN_1 =
            new User(START_SEQ + 14, "Ivanov", "ivanov@gmail.com", "1234567", RESTAURANT_1, true, Collections.singletonList(Role.ROLE_ADMIN));

    public static final User TEST_ADMIN_2 =
            new User(START_SEQ + 17, "Davidov", "davidov009@gmail.com", "1234567", RESTAURANT_3, true, Collections.singletonList(Role.ROLE_ADMIN));

    public static final DishTo DISH_TO_1 = new DishTo(START_SEQ + 3, "Soup", 15.0);
    public static final DishTo DISH_TO_2 = new DishTo(START_SEQ + 4, "French fries", 10.0);
    public static final DishTo DISH_TO_3 = new DishTo(START_SEQ + 7, "Coffee", 5.0);
    public static final DishTo DISH_TO_4 = new DishTo("Chicken Soup", 22.1);

    public static final Menu MENU_1 = new Menu(START_SEQ + 8, LocalDate.now(), RESTAURANT_1, 11);
    public static final Menu MENU_3 = new Menu(START_SEQ + 22, LocalDate.now(), RESTAURANT_1, 0);
    public static final Menu MENU_3_NEW = new Menu(null, LocalDate.now(), RESTAURANT_1, 0);

    public static final Dish DISH_1 = new Dish(START_SEQ + 3, "Soup");
    public static final Dish DISH_2 = new Dish(START_SEQ + 4, "French fries");
    public static final Dish DISH_3 = new Dish(START_SEQ + 5, "Hamburger");
    public static final Dish DISH_4 = new Dish(START_SEQ + 6, "Tea");
    public static final Dish DISH_5 = new Dish(START_SEQ + 7, "Coffee");
    public static final Dish DISH_6 = new Dish(START_SEQ + 26, "Chicken Soup");

    public static final MenuItem MENU_ITEM_1 = new MenuItem(START_SEQ + 9, MENU_1, DISH_1, 5.0);
    public static final MenuItem MENU_ITEM_2 = new MenuItem(START_SEQ + 10, MENU_1, DISH_2, 10.0);
    public static final MenuItem MENU_ITEM_3 = new MenuItem(START_SEQ + 11, MENU_1, DISH_3, 15.0);
    public static final MenuItem MENU_ITEM_4 = new MenuItem(START_SEQ + 12, MENU_1, DISH_4, 2.0);
    public static final MenuItem MENU_ITEM_5 = new MenuItem(START_SEQ + 13, MENU_1, DISH_5, 3.0);
    public static final MenuItem MENU_ITEM_6 = new MenuItem(START_SEQ + 26, MENU_1, DISH_6, 22.1);
    public static final MenuItem MENU_ITEM_7 = new MenuItem(START_SEQ + 24, MENU_1, DISH_5, 5.0);
    public static final MenuItem MENU_ITEM_8 = new MenuItem(START_SEQ + 25, MENU_1, DISH_1, 15.0);
    public static final MenuItem MENU_ITEM_9 = new MenuItem(START_SEQ + 27, MENU_1, DISH_3, 10.0);

    public static final List<Dish> DISH_LIST = Arrays.asList(DISH_1, DISH_2, DISH_3, DISH_4, DISH_5);
    public static final List<Menu> DAILY_MENU_LIST = Arrays.asList(MENU_1);

    public static final VoteLog VOTE_LOG_1 = new VoteLog(TEST_USER, RESTAURANT_1);//START_SEQ + 18

    public static final HistoryItem HISTORY_ITEM_1 = new HistoryItem(START_SEQ + 19, LocalDate.now().minusDays(1),
            RESTAURANT_1, "Soup:10.5 French fries:10.0 Coffee:5.3", 112);
    public static final HistoryItem HISTORY_ITEM_2 = new HistoryItem(START_SEQ + 20, LocalDate.now().minusDays(2),
            RESTAURANT_1, "Hamburger:13.12 Tea:3.0", 79);
    public static final HistoryItem HISTORY_ITEM_3 = new HistoryItem(START_SEQ + 21, LocalDate.now().minusDays(1),
            RESTAURANT_2, "Soup:7.0 Hamburger:11.0 Tea:3.4 Coffee:4.3", 179);

    public static final List<HistoryItem> HISTORY_ITEM_LIST = Arrays.asList(HISTORY_ITEM_1, HISTORY_ITEM_2, HISTORY_ITEM_3);

    public static Set<DishTo> dishes = new HashSet<>(Arrays.asList(DISH_TO_1, DISH_TO_2, DISH_TO_3, DISH_TO_4));


    public static <T> void assertMatch(T actual, T expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static <T> void assertMatch(Iterable<T> actual, T... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static <T> void assertMatch(Iterable<T> actual, Iterable<T> expected) {
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }

    public static void addMenuItemsToTestMenu(Menu menu, MenuItem... args) {
        Set<MenuItem> items = new HashSet<>(
                Arrays.asList(args));
        menu.setItems(items);
    }
}
