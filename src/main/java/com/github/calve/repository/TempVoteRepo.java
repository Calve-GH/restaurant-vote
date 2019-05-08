package com.github.calve.repository;

import com.github.calve.model.*;
import com.github.calve.to.DishTo;

import java.util.List;
import java.util.Set;

public interface TempVoteRepo {

    Menu getDailyMenu(Integer restaurantId);

    Menu saveDailyMenu(Set<DishTo> items, User admin);

    List<Menu> getVoteList();

    List<HistoryItem> getVoteHistory();

    List<HistoryItem> getVoteHistoryByRestaurant(Integer restaurantId);

    void vote(User user, Restaurant restaurant);

    void unlockVote(Integer userId);

    List<VoteLog> getAllLogs();

    List<Dish> getDishes();

    List<MenuItem> getMenuItems();

}
