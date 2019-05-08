package com.github.calve.repository;

import com.github.calve.model.*;
import com.github.calve.to.DishTo;

import java.time.LocalDate;
import java.util.*;

import static com.github.calve.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;


public class VoteTestData {

    static final User testUser =
            new User(START_SEQ + 14, "Bibiziana", "makaka@gmail.com", "9876543", null, true, new Date(), Collections.singletonList(Role.ROLE_USER));

    static final Restaurant restaurant_1 = new Restaurant(START_SEQ, "Sweet bobaleh");
    static final Restaurant restaurant_2 = new Restaurant(START_SEQ + 1, "ITAKA");

    static final User testAdmin =
            new User(START_SEQ + 13, "Ivanov", "ivanov@gmail.com", "1234567", restaurant_1, true, new Date(), Collections.singletonList(Role.ROLE_ADMIN));

    static final DishTo DISH_TO_1 = new DishTo(START_SEQ + 2, "Soup", 15.0);
    static final DishTo DISH_TO_2 = new DishTo(START_SEQ + 4, "French fries", 10.0);
    static final DishTo DISH_TO_3 = new DishTo(START_SEQ + 6, "Coffee", 5.0);
    static final DishTo DISH_TO_4 = new DishTo("Chicken Soup", 22.1);

    static final Menu MENU_1 = new Menu(START_SEQ + 7, LocalDate.now(), restaurant_1);
    static final Menu MENU_2 = new Menu(START_SEQ + 19, LocalDate.now(), restaurant_2);
    static final Menu MENU_3 = new Menu(START_SEQ + 23, LocalDate.now(), restaurant_1);


/*    Menu{id=100023, date=2019-05-08, restaurant=Restaurant{id=100000, name='Sweet bobaleh'},
items=[MenuItem{id=100025dish=Chicken Soup, price=22.1}, MenuItem{id=100027dish=Soup, price=15.0},
 MenuItem{id=100026dish=coffee, price=5.0}, MenuItem{id=100028dish=hamburger, price=10.0}], voteCount=0}*/

    static final Dish DISH_1 = new Dish(START_SEQ + 2, "Soup");
    static final Dish DISH_2 = new Dish(START_SEQ + 3, "French fries");
    static final Dish DISH_3 = new Dish(START_SEQ + 4, "hamburger");
    static final Dish DISH_4 = new Dish(START_SEQ + 5, "tea");
    static final Dish DISH_5 = new Dish(START_SEQ + 6, "coffee");
    static final Dish DISH_6 = new Dish(START_SEQ + 24, "Chicken Soup");

    static final MenuItem MENU_ITEM_1 = new MenuItem(START_SEQ + 8, MENU_1, DISH_1, 5.0);
    static final MenuItem MENU_ITEM_2 = new MenuItem(START_SEQ + 9, MENU_1, DISH_2, 10.0);
    static final MenuItem MENU_ITEM_3 = new MenuItem(START_SEQ + 10, MENU_1, DISH_3, 15.0);
    static final MenuItem MENU_ITEM_4 = new MenuItem(START_SEQ + 11, MENU_1, DISH_4, 2.0);
    static final MenuItem MENU_ITEM_5 = new MenuItem(START_SEQ + 12, MENU_1, DISH_5, 3.0);
    static final MenuItem MENU_ITEM_6 = new MenuItem(START_SEQ + 25, MENU_1, DISH_6, 22.1);
    static final MenuItem MENU_ITEM_7 = new MenuItem(START_SEQ + 26, MENU_1, DISH_5, 5.0);
    static final MenuItem MENU_ITEM_8 = new MenuItem(START_SEQ + 27, MENU_1, DISH_1, 15.0);
    static final MenuItem MENU_ITEM_9 = new MenuItem(START_SEQ + 28, MENU_1, DISH_3, 10.0);


    static final VoteLog VOTE_LOG_1 = new VoteLog(testUser, restaurant_1);

    static final HistoryItem HISTORY_ITEM_1 = new HistoryItem(START_SEQ + 20, LocalDate.now().minusDays(1),
            restaurant_1, "SOME DISHES LIST WITH PRICES 1", 112);
    static final HistoryItem HISTORY_ITEM_2 = new HistoryItem(START_SEQ + 21, LocalDate.now().minusDays(2),
            restaurant_1, "SOME DISHES LIST WITH PRICES 2", 79);
    static final HistoryItem HISTORY_ITEM_3 = new HistoryItem(START_SEQ + 22, LocalDate.now().minusDays(1),
            restaurant_2, "SOME DISHES LIST WITH PRICES 3", 179);

    /*weg
    Menu{id=100007, date=2019-05-07, ,
    items=[MenuItem{dish=French fries, price=10.0}, MenuItem{dish=Soup, price=5.0},
    MenuItem{dish=tea, price=2.0}, MenuItem{dish=hamburger, price=15.0},
    MenuItem{dish=coffee, price=3.0}], voteCount=11}
*/

    static Set<DishTo> dishes = new HashSet<>(Arrays.asList(DISH_TO_1, DISH_TO_2, DISH_TO_3, DISH_TO_4));

    static <T> void assertMatch(T actual, T expected) {
        assertThat(actual).isEqualTo(expected);
    }

    static <T> void assertMatch(Iterable<T> actual, T... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    static <T> void assertMatch(Iterable<T> actual, Iterable<T> expected) {
        assertThat(actual).usingDefaultElementComparator().isEqualTo(expected);
    }
}
