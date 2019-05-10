package com.github.calve.repository;

import com.github.calve.model.Dish;
import com.github.calve.model.Menu;
import com.github.calve.model.User;
import com.github.calve.to.DishTo;

import java.util.List;
import java.util.Set;

public interface MenuRepository {

    Menu getDailyMenu(Integer restaurantId);

    Menu saveDailyMenu(Set<DishTo> items, Integer adminId);

    List<Dish> getDishes();

    Menu getWithMI(Integer restaurantId);
}
