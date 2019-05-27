package com.github.calve.repository;

import com.github.calve.model.*;
import com.github.calve.repository.datajpa.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
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
    private CrudMenuItemRepository menuItemRepo;
    @Autowired
    private CrudDishRepository dishRepo;

    @Autowired(required = false)
    private JpaUtil jpaUtil;

    @Autowired
    private CacheManager cacheManager;

    @Value("${dish.list.min.size}")
    private Integer minDishes;
    @Value("${dish.list.max.size}")
    private Integer maxDishes;

    @Test
    void getAllMenus() {
        assertMatch(menuRepo.findAll().size(), 5);
    }

    @Test
    void getAllMenusByRestaurantId() {
        assertMatch(menuRepo.findAllByRestaurantId(RESTAURANT_1.getId()).size(), 3);
    }

    @Test
    void getAllMenusByDate() {
        assertMatch(menuRepo.findAllByDate(LocalDate.now()).size(), 2);
    }

    @Test
    void getMenuByDateAndRestaurantId() {
        Menu menu = menuRepo.findByDateAndRestaurantId(LocalDate.now(), RESTAURANT_1.getId());
        addMenuItemsToTestMenu(MENU_1, MENU_ITEM_1, MENU_ITEM_2, MENU_ITEM_3, MENU_ITEM_4, MENU_ITEM_5);
        assertMatch(menu, MENU_1);
        assertMatch(menu.getRestaurant(), MENU_1.getRestaurant());
        assertMatch(menu.getItems(), MENU_1.getItems());
    }

    @Test
    void saveMenuSuccess() {
        MENU_6_NEW.setItems(MENU_DISH_LIST_TRANSIENT);
        Menu saved = menuRepo.save(MENU_6_NEW);
        MENU_6_NEW.setId(saved.getId());
        MENU_6_NEW.setItems(MENU_DISH_LIST_PERSISTENT);
        assertMatch(saved, MENU_6_NEW);
        assertMatch(saved.getRestaurant(), MENU_6_NEW.getRestaurant());
        assertMatch(saved.getItems(), MENU_6_NEW.getItems());
    }

    @Test
    void saveMenuReWriteSuccess() {
        for(MenuItem item : MENU_DISH_LIST_TRANSIENT) {
            item.setMenu(MENU_1);
        }
        MENU_1.setItems(MENU_DISH_LIST_TRANSIENT);
        Menu saved = menuRepo.save(MENU_1);
        assertMatch(saved, MENU_1);
        assertMatch(menuItemRepo.findByMenuIdOrderByIdAsc(saved.getId()), saved.getItems().stream()
                .sorted(Comparator.comparingInt(AbstractBaseEntity::getId)).collect(Collectors.toList()));
    }

    @Test
    void getVoteList() {
        List<Menu> voteList = menuRepo.findAllByDate(LocalDate.now());
        addMenuItemsToTestMenu(MENU_1, MENU_ITEM_1, MENU_ITEM_2, MENU_ITEM_3, MENU_ITEM_4, MENU_ITEM_5);
        addMenuItemsToTestMenu(MENU_2, MENU_ITEM_6, MENU_ITEM_7);

        assertMatch(voteList.size(), 2);
        assertMatch(voteList, Arrays.asList(MENU_1, MENU_2));
        assertMatch(voteList.stream().map(Menu::getRestaurant)
                .collect(Collectors.toList()), Arrays.asList(MENU_1.getRestaurant(), MENU_2.getRestaurant()));
        assertMatch(voteList.stream().map(Menu::getItems)
                .collect(Collectors.toList()), Arrays.asList(MENU_1.getItems(), MENU_2.getItems()));
    }

    @Test
    void getVoteHistory() {
        List<HistoryItem> voteHistory = historyRepo.findAll();
        assertMatch(voteHistory, sortByDate(HISTORY_ITEM_1, HISTORY_ITEM_3, HISTORY_ITEM_2));
    }

    @Test
    void getVoteHistoryByRestaurant() {
        assertMatch(historyRepo.findByRestaurantId(RESTAURANT_1.getId()), sortByDate(HISTORY_ITEM_1, HISTORY_ITEM_2));
    }

    private List<HistoryItem> sortByDate(HistoryItem... array) {
        return Arrays.stream(array).sorted(Comparator.comparing(HistoryItem::getDate).reversed()).collect(Collectors.toList());
    }

    @Test
    void voteUserSuccess() {
        User user = userRepo.getOne(TEST_USER_3.getId());
        Restaurant restaurant = restaurantRepo.getOne(RESTAURANT_1.getId());
        assertTrue(restaurant.getMenuExist());
        VoteLog voteLog = new VoteLog(user, restaurant);
        voteLogRepo.save(voteLog);
        List<String> list = voteLogRepo.findAll().stream().map(VoteLog::toString).collect(Collectors.toList());
        assertTrue(list.contains(VOTE_LOG_1.toString())); //WRONG LDT FORMAT Сравнение по строкам
        // убрал из User.toString дату ибо из БД тянется с наносеками а там различия, пароль тоже убрал
        assertEquals(menuRepo.findByDateAndRestaurantId(LocalDate.now(), RESTAURANT_1.getId()).getVoteCount(), 12);
    }

    @Test
    void reVoteUserSuccess() {
        User user = userRepo.getOne(TEST_ADMIN_1.getId());
        Restaurant restaurant = restaurantRepo.getOne(RESTAURANT_2.getId());
        assertTrue(restaurant.getMenuExist());
        VoteLog voteLog = new VoteLog(user, restaurant);
        voteLogRepo.delete(voteLog.getUser().getId(), voteLog.getDate());//TODO TEST On CHECK
        voteLogRepo.save(voteLog);
        voteLogRepo.findAll();//TODO
        assertEquals(menuRepo.findByDateAndRestaurantId(LocalDate.now(), RESTAURANT_1.getId()).getVoteCount(), 10);
        assertEquals(menuRepo.findByDateAndRestaurantId(LocalDate.now(), RESTAURANT_2.getId()).getVoteCount(), 13);
    }

    @Test
    void getAllDishes() {
        assertEquals(dishRepo.findAll().size(), 5);
    }
    @Test
    void saveDishSuccess() {
        Dish saved = dishRepo.save(NEW_DISH_1);
        NEW_DISH_1.setId(saved.getId());
        assertEquals(saved, NEW_DISH_1);
    }
    @Test
    void deleteDishById() {
        assertEquals(dishRepo.delete(DISH_1.getId()), 1);
        assertEquals(dishRepo.findAll().size(), 4);
    }

    @Test
    void getAllRestaurants() {
        assertEquals(restaurantRepo.findAll().size(), 3);
    }
    @Test
    void saveRestaurantSuccess() {
        Restaurant saved = restaurantRepo.save(NEW_RESTAURANT_1);
        NEW_RESTAURANT_1.setId(saved.getId());
        assertEquals(saved, NEW_RESTAURANT_1);
    }
    @Test
    void deleteRestaurantById() {
        assertEquals(restaurantRepo.delete(RESTAURANT_1.getId()), 1);
        assertEquals(restaurantRepo.findAll().size(), 2);
    }

    @BeforeEach
    void setUp() {
/*        cacheManager.getCache("users").clear();
        cacheManager.getCache("dishes").clear();
        cacheManager.getCache("menus").clear();
        cacheManager.getCache("history").clear();
        cacheManager.getCache("restaurants").clear();*/
        if (jpaUtil != null) {
            jpaUtil.clear2ndLevelHibernateCache();
        }
    }
}