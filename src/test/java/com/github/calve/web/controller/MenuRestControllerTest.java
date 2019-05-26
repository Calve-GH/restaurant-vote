package com.github.calve.web.controller;

import com.github.calve.model.Dish;
import com.github.calve.model.Menu;
import com.github.calve.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;

import static com.github.calve.TestUtil.*;
import static com.github.calve.VoteTestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class MenuRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = MenuRestController.REST_URL + "/";

    @Test
    void testGetAuthenticatedFail() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testGetDailyMenuSuccess() throws Exception {
        mockMvc.perform(get(MenuRestController.REST_URL)
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(readFromJsonMvcResult(result, Menu.class), MENU_1));
    }

    @Test
    void testGetDishesSuccess() throws Exception {
        mockMvc.perform(get(MenuRestController.REST_URL + "/dishes")
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertThat(readListFromJsonMvcResult(result, Dish.class)).isEqualTo(DISH_LIST));
    }

    @Test
    void testSaveMenuSuccess() throws Exception {

        ResultActions actions = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(Arrays.asList(DISH_TO_1, DISH_TO_2, DISH_TO_3, DISH_TO_4)))
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isCreated());

        Menu returned = readFromJson(actions, Menu.class);
        //MENU_3_NEW.setId(returned.getId());
        assertMatch(returned, MENU_3);
        assertMatch(returned.getRestaurant(), MENU_3.getRestaurant());
        addMenuItemsToTestMenu(MENU_3, MENU_ITEM_6, MENU_ITEM_7, MENU_ITEM_8, MENU_ITEM_9);
        assertMatch(returned.getItems(), MENU_3.getItems());
    }

    @Test
    void testSaveMenuOutOfRangeFail() throws Exception {
        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(Arrays.asList(DISH_TO_1)))
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}
