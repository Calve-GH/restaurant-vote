package com.github.calve.repository;

import com.github.calve.model.*;
import com.github.calve.repository.datajpa.*;
import com.github.calve.to.DishTo;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class TempVoteRepoImpl implements TempVoteRepo {

    @Autowired
    private CrudMenuRepository menuRepo;
    @Autowired
    private CrudVoteLogRepo voteLogRepo;
    @Autowired
    private CrudHistoryRepo historyRepo;
    @Autowired
    private CrudDishRepository dishRepo;
    @Autowired
    private CrudMenuItemRepository menuItemRepo;

    @Override
    public Menu getDailyMenu(Integer restaurantId) {
        return menuRepo.getMenuByDateAndRestaurantId(LocalDate.now(), restaurantId);
    }

    @Transactional
    @Override
    public Menu saveDailyMenu(Set<DishTo> dishes, User admin) {
        if (dishes.size() >= 2 && dishes.size() <= 5) {//TODO HARD CODE
            menuRepo.deleteByRestaurantId(admin.getRestaurant().getId()); //CASCADE TO MENU ITEMS
            Menu menu = new Menu(LocalDate.now(), admin.getRestaurant());
            menu = menuRepo.save(menu);
            Set<MenuItem> items = parseMenuItems(dishes, menu);
            menu.setItems(items);
            menuRepo.save(menu);
            return menu;
        }
        return null;
    }

    @Override
    public List<Menu> getVoteList() {
        return menuRepo.findAllByDate(LocalDate.now());
    }

    @Override
    public List<HistoryItem> getVoteHistory() {
        return historyRepo.findAll();
    }

    @Override
    public List<HistoryItem> getVoteHistoryByRestaurant(Integer restaurantId) {
        return historyRepo.findByRestaurantId(restaurantId);
    }

    @Override
    public void vote(User user, Restaurant restaurant) {
        VoteLog voteLog = new VoteLog(user, restaurant);
        voteLogRepo.save(voteLog);
    }

    @Override
    public void unlockVote(Integer userId) {
        voteLogRepo.delete(userId);
    }

    @Override
    public List<VoteLog> getAllLogs() {
        return voteLogRepo.findAll();
    }

    @Override
    public List<Dish> getDishes() {
        return dishRepo.findAll();
    }

    @Override
    public List<MenuItem> getMenuItems() {
        return menuItemRepo.findAll();
    }

    @NotNull
    private Set<MenuItem> parseMenuItems(Set<DishTo> dishes, Menu menu) {
        Set<MenuItem> items = new HashSet<>();
        for (DishTo dishTo : dishes) {
            Dish dishWithId;
            if (dishTo.isNew()) {
                dishWithId = dishRepo.save(new Dish(dishTo.getName()));
            } else {
                dishWithId = dishRepo.findById(dishTo.getId()).orElse(null);
            }
            items.add(new MenuItem(menu, dishWithId, dishTo.getPrice()));
        }
        return items;
    }
}
