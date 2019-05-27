package com.github.calve.web.controller;

import com.github.calve.model.Dish;
import com.github.calve.model.Menu;
import com.github.calve.model.Restaurant;
import com.github.calve.repository.MenuUtil;
import com.github.calve.repository.datajpa.CrudRestaurantRepo;
import com.github.calve.to.MenuTo;
import com.github.calve.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import javax.persistence.Access;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static com.github.calve.TestUtil.*;
import static com.github.calve.VoteTestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class MenuRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = MenuRestController.REST_URL + "/";
    private static final String TODAY = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    @Autowired
    private CrudRestaurantRepo restaurantRepo;

    @Test
    void testGetAuthenticatedFail() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testGetDailyMenuByRestaurantSuccess() throws Exception {
        addMenuItemsToTestMenu(MENU_1, MENU_ITEM_1, MENU_ITEM_2, MENU_ITEM_3, MENU_ITEM_4, MENU_ITEM_5);

        mockMvc.perform(get(REST_URL + "menu?date=" + TODAY +"&restaurantId=" + RESTAURANT_1.getId())
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(readListFromJsonMvcResult(result, Menu.class), Collections.singletonList(MENU_1)));
    }

    @Test
    void testGetAllDailyMenuSuccess() throws Exception {
        addMenuItemsToTestMenu(MENU_1, MENU_ITEM_1, MENU_ITEM_2, MENU_ITEM_3, MENU_ITEM_4, MENU_ITEM_5);
        addMenuItemsToTestMenu(MENU_2, MENU_ITEM_6, MENU_ITEM_7);



        mockMvc.perform(get(REST_URL + "menu?date=" + TODAY)
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(readListFromJsonMvcResult(result, Menu.class), Arrays.asList(MENU_1, MENU_2)));
    }

    @Test
    void testSaveMenuSuccess() throws Exception {
        MenuTo menuTo = new MenuTo(LocalDate.now(), RESTAURANT_3, new ArrayList<>(MENU_DISH_LIST_TRANSIENT));
        Menu expected = MenuUtil.createNewFromTo(menuTo);
        expected.setItems(MENU_DISH_LIST_PERSISTENT);
        ResultActions actions = mockMvc.perform(post(REST_URL + "menu")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(menuTo))
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isCreated());

        Menu returned = readFromJson(actions, Menu.class);
        expected.setId(returned.getId());
        assertMatch(returned, expected);
        assertMatch(returned.getRestaurant(), expected.getRestaurant());
        assertMatch(returned.getItems(), expected.getItems());
    }

    @Test
    void testSaveMenuOutOfRangeFail() throws Exception {
        MenuTo menuTo = new MenuTo(LocalDate.now(), RESTAURANT_3, Arrays.asList(MENU_ITEM_16_NEW));
        Menu expected = MenuUtil.createNewFromTo(menuTo);
        expected.setItems(MENU_DISH_LIST_PERSISTENT);
        ResultActions actions = mockMvc.perform(post(REST_URL + "menu")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(menuTo))
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void testUpdateMenuByIdSuccess() throws Exception {
        MenuTo menuTo = new MenuTo(MENU_2.getId(), LocalDate.now(), RESTAURANT_2,
                new ArrayList<>(MENU_DISH_LIST_TRANSIENT), 12);
        mockMvc.perform(put(REST_URL + "menu/" + MENU_2.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(menuTo))
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteMenuByIdSuccess() throws Exception {
        mockMvc.perform(delete(REST_URL + "menu/" + MENU_2.getId())
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetDishesSuccess() throws Exception {
        mockMvc.perform(get(MenuRestController.REST_URL + "/dish")
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertThat(readListFromJsonMvcResult(result, Dish.class)).isEqualTo(DISH_LIST));
    }

    @Test
    void testDeleteDishByIdSuccess() throws Exception {
        mockMvc.perform(delete(REST_URL + "dish/" + DISH_1.getId())
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void getRestaurantsSuccess() throws Exception {
        mockMvc.perform(get(REST_URL + "restaurant")
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertThat(readListFromJsonMvcResult(result, Restaurant.class))
                        .isEqualTo(Arrays.asList(RESTAURANT_1, RESTAURANT_2, RESTAURANT_3)));
    }

    @Test
    void testDeleteRestaurantByIdSuccess() throws Exception {
        mockMvc.perform(delete(REST_URL + "restaurant/" + RESTAURANT_1.getId())
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
    @Test
    void testUpdateRestaurantSuccess() throws Exception {
        String restaurantName = RESTAURANT_1.getName();
        RESTAURANT_1.setName("NEW RESTAURANT NAME");
        mockMvc.perform(post(REST_URL + "restaurant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(RESTAURANT_1))
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isCreated());
        RESTAURANT_1.setName(restaurantName);
    }
}
