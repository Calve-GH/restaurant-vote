package com.github.calve;

import com.github.calve.model.*;
import com.github.calve.to.DishTo;

import java.time.LocalDate;
import java.util.*;

import static com.github.calve.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;


public class VoteTestData {

    public static final Restaurant RESTAURANT_1 = new Restaurant(START_SEQ, "Sweet bobaleh");
    public static final Restaurant RESTAURANT_2 = new Restaurant(START_SEQ + 1, "ITAKA");
    public static final Restaurant RESTAURANT_3 = new Restaurant(START_SEQ + 2, "Hunter Village");

    public static final Dish DISH_1 = new Dish(START_SEQ + 3, "Soup");
    public static final Dish DISH_2 = new Dish(START_SEQ + 4, "French fries");
    public static final Dish DISH_3 = new Dish(START_SEQ + 5, "Hamburger");
    public static final Dish DISH_4 = new Dish(START_SEQ + 6, "Tea");
    public static final Dish DISH_5 = new Dish(START_SEQ + 7, "Coffee");
//    public static final Dish DISH_6 = new Dish(START_SEQ + 26, "Chicken Soup");

    public static final Menu MENU_1 = new Menu(START_SEQ + 8, LocalDate.now(), RESTAURANT_1, 11);
    public static final Menu MENU_2 = new Menu(START_SEQ + 9, LocalDate.now(), RESTAURANT_2, 12);
    public static final Menu MENU_3 = new Menu(START_SEQ + 10, LocalDate.now().plusDays(1), RESTAURANT_3, 0);
    public static final Menu MENU_4 = new Menu(START_SEQ + 11, LocalDate.now().plusDays(1), RESTAURANT_1, 0);
    public static final Menu MENU_5 = new Menu(START_SEQ + 12, LocalDate.now().plusDays(2), RESTAURANT_1, 0);

    /*    public static final Menu MENU_3 = new Menu(START_SEQ + 22, LocalDate.now(), RESTAURANT_1, 0);
    public static final Menu MENU_3_NEW = new Menu(null, LocalDate.now(), RESTAURANT_1, 0);*/

    public static final MenuItem MENU_ITEM_1 = new MenuItem(START_SEQ + 13, MENU_1, DISH_1, 5.1);
    public static final MenuItem MENU_ITEM_2 = new MenuItem(START_SEQ + 14, MENU_1, DISH_2, 10.2);
    public static final MenuItem MENU_ITEM_3 = new MenuItem(START_SEQ + 15, MENU_1, DISH_3, 15.3);
    public static final MenuItem MENU_ITEM_4 = new MenuItem(START_SEQ + 16, MENU_1, DISH_4, 2.0);
    public static final MenuItem MENU_ITEM_5 = new MenuItem(START_SEQ + 17, MENU_1, DISH_5, 3.0);
    public static final MenuItem MENU_ITEM_6 = new MenuItem(START_SEQ + 18, MENU_2, DISH_1, 5.4);
    public static final MenuItem MENU_ITEM_7 = new MenuItem(START_SEQ + 19, MENU_2, DISH_2, 10.7);
    public static final MenuItem MENU_ITEM_8 = new MenuItem(START_SEQ + 20, MENU_3, DISH_2, 10.2);
    public static final MenuItem MENU_ITEM_9 = new MenuItem(START_SEQ + 21, MENU_3, DISH_3, 15.7);
    public static final MenuItem MENU_ITEM_10 = new MenuItem(START_SEQ + 22, MENU_1, DISH_3, 15.3);
    public static final MenuItem MENU_ITEM_11 = new MenuItem(START_SEQ + 23, MENU_1, DISH_4, 2.0);
    public static final MenuItem MENU_ITEM_12 = new MenuItem(START_SEQ + 24, MENU_1, DISH_5, 3.0);
    public static final MenuItem MENU_ITEM_13 = new MenuItem(START_SEQ + 25, MENU_1, DISH_3, 15.1);
    public static final MenuItem MENU_ITEM_14 = new MenuItem(START_SEQ + 26, MENU_1, DISH_4, 2.1);
    public static final MenuItem MENU_ITEM_15 = new MenuItem(START_SEQ + 27, MENU_1, DISH_5, 3.2);

    public static final User TEST_ADMIN_1 =
            new User(START_SEQ + 28, "Ivanov", "ivanov@gmail.com", "1234567", true, Collections.singletonList(Role.ROLE_ADMIN));
    public static final User TEST_USER_1 =
            new User(START_SEQ + 29, "Petrov", "petrov@gmail.com", "9876543", true, Collections.singletonList(Role.ROLE_USER));
    public static final User TEST_USER_2 =
            new User(START_SEQ + 30, "Sidorov", "sidirov@mail.ru", "12351514", true, Collections.singletonList(Role.ROLE_USER));
    public static final User TEST_USER_3 =
            new User(START_SEQ + 31, "Davidov", "davidov009@gmail.com", "1234567", true, Collections.singletonList(Role.ROLE_USER));

    public static final VoteLog VOTE_LOG_1 = new VoteLog(TEST_ADMIN_1, RESTAURANT_1);   //32
    public static final VoteLog VOTE_LOG_2 = new VoteLog(TEST_USER_1, RESTAURANT_1);    //33
    public static final VoteLog VOTE_LOG_3 = new VoteLog(TEST_USER_2, RESTAURANT_2);    //34

    public static final HistoryItem HISTORY_ITEM_1 = new HistoryItem(START_SEQ + 35, LocalDate.now().minusDays(1),
            RESTAURANT_1, "Soup:10.5 French fries:10.0 Coffee:5.3", 112);
    public static final HistoryItem HISTORY_ITEM_2 = new HistoryItem(START_SEQ + 36, LocalDate.now().minusDays(2),
            RESTAURANT_1, "Hamburger:13.12 Tea:3.0", 79);
    public static final HistoryItem HISTORY_ITEM_3 = new HistoryItem(START_SEQ + 37, LocalDate.now().minusDays(1),
            RESTAURANT_2, "Soup:7.0 Hamburger:11.0 Tea:3.4 Coffee:4.3", 179);
//new entities //100037

    public static final Menu MENU_6_NEW = new Menu(null, LocalDate.now(), RESTAURANT_3, 0); //100038

    public static final MenuItem MENU_ITEM_16_NEW = new MenuItem(null, DISH_3, 15.1);//100039
    public static final MenuItem MENU_ITEM_17_NEW = new MenuItem(null, DISH_4, 2.1);//100040
    public static final MenuItem MENU_ITEM_18_NEW = new MenuItem(null, DISH_5, 3.2);//100041
    public static final MenuItem MENU_ITEM_16 = new MenuItem(START_SEQ + 39, MENU_1, DISH_3, 15.1);//100039
    public static final MenuItem MENU_ITEM_17 = new MenuItem(START_SEQ + 40, MENU_1, DISH_4, 2.1);//100040
    public static final MenuItem MENU_ITEM_18 = new MenuItem(START_SEQ + 41, MENU_1, DISH_5, 3.2);//100041

    public static final Set<MenuItem> MENU_DISH_LIST_TRANSIENT = new HashSet<>(Arrays.asList(MENU_ITEM_16_NEW,
            MENU_ITEM_17_NEW, MENU_ITEM_18_NEW));
    public static final Set<MenuItem> MENU_DISH_LIST_PERSISTENT = new HashSet<>(Arrays.asList(MENU_ITEM_16,
            MENU_ITEM_17, MENU_ITEM_18));

    public static final Dish NEW_DISH_1 = new Dish("Sandwich");

    public static final Restaurant NEW_RESTAURANT_1 = new Restaurant("CoCo Bongo");

    //-----------------------------------
    public static final DishTo DISH_TO_1 = new DishTo(START_SEQ + 3, 15.0);
    public static final DishTo DISH_TO_2 = new DishTo(START_SEQ + 4, 10.0);
    public static final DishTo DISH_TO_3 = new DishTo(START_SEQ + 7, 5.0);
    public static final DishTo DISH_TO_4 = new DishTo(START_SEQ + 24, 22.1);//TODO WRONG ID

    public static final List<Dish> DISH_LIST = Arrays.asList(DISH_1, DISH_2, DISH_3, DISH_4, DISH_5);
    public static final List<Menu> DAILY_MENU_LIST = Arrays.asList(MENU_1);

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
