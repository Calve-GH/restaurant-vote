package com.github.calve.repository;

import com.github.calve.model.HistoryItem;
import com.github.calve.model.Menu;
import com.github.calve.model.Restaurant;
import com.github.calve.model.User;

import java.util.List;

public interface VoteRepository {
    List<Menu> getVoteList();

    List<HistoryItem> getVoteHistory();

    List<HistoryItem> getVoteHistoryByRestaurant(Integer restaurantId);

    void vote(Integer userId, Integer restaurantId);

    void unlockVote(Integer userId);
}
