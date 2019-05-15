package com.github.calve.web.controller;

import com.github.calve.UserUtil;
import com.github.calve.model.*;
import com.github.calve.repository.datajpa.CrudMenuRepository;
import com.github.calve.repository.datajpa.CrudRestaurantRepo;
import com.github.calve.repository.datajpa.CrudVoteLogRepo;
import com.github.calve.service.UserService;
import com.github.calve.to.UserTo;
import com.github.calve.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.calve.TestUtil.*;
import static com.github.calve.VoteTestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VoteRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VoteRestController.REST_URL + "/";
    private static final Comparator<HistoryItem> HISTORY_ITEM_COMPARATOR_DESC = Comparator.comparing(HistoryItem::getDate).reversed();

    @Autowired
    private CrudVoteLogRepo voteLogRepo;
    @Autowired
    private CrudMenuRepository menuRepo;
    @Autowired
    private UserService userService;

    @Test
    void voteUserSuccess() throws Exception {
        mockMvc.perform(put(REST_URL + "vote/" + RESTAURANT_1.getId())
                .with(userHttpBasic(TEST_USER)))
                .andDo(print())
                .andExpect(status().isNoContent());

        List<String> list = voteLogRepo.findAll().stream().map(VoteLog::toString).collect(Collectors.toList());
        assertTrue(list.contains(VOTE_LOG_1.toString()));
    }

    @Test
    void voteByWrongRestaurantIdFail() throws Exception {
        mockMvc.perform(put(REST_URL + "vote/123")
                .with(userHttpBasic(TEST_USER)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void unlockVoteSuccess() throws Exception {
        mockMvc.perform(delete(REST_URL + "vote/")
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertEquals(menuRepo.getWithMI(RESTAURANT_1.getId()).getVoteCount(), 10);

    }

    @Test
    void getVoteListSuccess() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(TEST_USER)))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertThat(readListFromJsonMvcResult(result, Menu.class)).isEqualTo(DAILY_MENU_LIST));
    }

    @Test
    void getVoteListAuthenticated() throws Exception {
        mockMvc.perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getRestaurantsSuccess() throws Exception {
        mockMvc.perform(get(REST_URL + "vote/restaurants")
                .with(userHttpBasic(TEST_USER)))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertThat(readListFromJsonMvcResult(result, Restaurant.class))
                        .isEqualTo(Arrays.asList(RESTAURANT_1, RESTAURANT_2, RESTAURANT_3)));
    }

    @Test
    void getVoteHistorySuccess() throws Exception {
        Collections.sort(HISTORY_ITEM_LIST, HISTORY_ITEM_COMPARATOR_DESC);
        mockMvc.perform(get(REST_URL + "vote/history")
                .with(userHttpBasic(TEST_USER)))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertThat(readListFromJsonMvcResult(result, HistoryItem.class)).isEqualTo(HISTORY_ITEM_LIST));
    }

    @Test
    void getVoteHistoryByRestaurantSuccess() throws Exception {
        List<HistoryItem> listDesc = Arrays.asList(HISTORY_ITEM_1, HISTORY_ITEM_2);
        Collections.sort(listDesc, HISTORY_ITEM_COMPARATOR_DESC);
        mockMvc.perform(get(REST_URL + "vote/history/" + RESTAURANT_1.getId())
                .with(userHttpBasic(TEST_USER)))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertThat(readListFromJsonMvcResult(result, HistoryItem.class)).isEqualTo(listDesc));
    }

    @Test
    void testGetForbidden() throws Exception {
        mockMvc.perform(get(MenuRestController.REST_URL)
                .with(userHttpBasic(TEST_USER)))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    void testRegisterSuccess() throws Exception {
        UserTo createdTo = new UserTo(null, "newName", "newemail@ya.ru", "newPassword", "Mandondo");

        ResultActions action = mockMvc.perform(post(REST_URL + "register").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(createdTo)))
                .andDo(print())
                .andExpect(status().isCreated());
        User returned = readFromJson(action, User.class);

        User created = UserUtil.createNewFromTo(createdTo);
        created.setId(returned.getId());

        assertMatch(returned, created);
        User user = userService.getByEmail("newemail@ya.ru");
        assertMatch(user, created);
    }
}