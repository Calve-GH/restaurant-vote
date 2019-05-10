package com.github.calve.web.controller;

import com.github.calve.model.HistoryItem;
import com.github.calve.model.Menu;
import com.github.calve.model.VoteLog;
import com.github.calve.repository.MenuRepository;
import com.github.calve.repository.TempVoteRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.calve.TestUtil.readListFromJsonMvcResult;
import static com.github.calve.TestUtil.userHttpBasic;
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
    private TempVoteRepo voteRepo;
    @Autowired
    private MenuRepository menuRepository;

    @Test
    void voteUserSuccess() throws Exception {
        mockMvc.perform(put(REST_URL + "vote/" + RESTAURANT_1.getId())
                .with(userHttpBasic(TEST_USER)))
                .andDo(print())
                .andExpect(status().isNoContent());

        List<String> list = voteRepo.getAllLogs().stream().map(VoteLog::toString).collect(Collectors.toList());
        assertTrue(list.contains(VOTE_LOG_1.toString()));
    }

    @Test
    void unlockVote() throws Exception {
        mockMvc.perform(delete(REST_URL + "vote/")
                .with(userHttpBasic(TEST_ADMIN_1)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertEquals(menuRepository.getDailyMenu(RESTAURANT_1.getId()).getVoteCount(), 10);

    }

    @Test
    void getVoteList() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(TEST_USER)))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertThat(readListFromJsonMvcResult(result, Menu.class)).isEqualTo(DAILY_MENU_LIST));
    }

    @Test
    void getVoteHistory() throws Exception {
        Collections.sort(HISTORY_ITEM_LIST, HISTORY_ITEM_COMPARATOR_DESC);
        mockMvc.perform(get(REST_URL + "history")
                .with(userHttpBasic(TEST_USER)))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertThat(readListFromJsonMvcResult(result, HistoryItem.class)).isEqualTo(HISTORY_ITEM_LIST));
    }

    @Test
    void getVoteHistoryByRestaurant() throws Exception {
        List<HistoryItem> listDesc = Arrays.asList(HISTORY_ITEM_1, HISTORY_ITEM_2);
        Collections.sort(listDesc, HISTORY_ITEM_COMPARATOR_DESC);
        mockMvc.perform(get(REST_URL + "history/" + RESTAURANT_1.getId())
                .with(userHttpBasic(TEST_USER)))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertThat(readListFromJsonMvcResult(result, HistoryItem.class)).isEqualTo(listDesc));
    }

    @Test
    void register() {
    }
}