package com.github.calve.repository;

import com.github.calve.model.Dish;
import com.github.calve.model.Menu;
import com.github.calve.model.MenuItem;
import com.github.calve.model.User;
import com.github.calve.repository.datajpa.CrudDishRepository;
import com.github.calve.repository.datajpa.CrudMenuRepository;
import com.github.calve.repository.datajpa.CrudUserRepository;
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
public class DataJpaMenuRepositoryImpl implements MenuRepository {

    @Autowired
    private CrudMenuRepository menuRepo;
    @Autowired
    private CrudDishRepository dishRepo;
    @Autowired
    private CrudUserRepository userRepo;

    @Override
    public Menu getDailyMenu(Integer restaurantId) {
        return menuRepo.getMenuByDateAndRestaurantId(LocalDate.now(), restaurantId);
    }

    @Transactional
    @Override
    public Menu saveDailyMenu(Set<DishTo> dishes, Integer adminId) {
        if (dishes.size() >= 2 && dishes.size() <= 5) {//TODO HARD CODE
            System.out.println(adminId);
            User admin = userRepo.findById(adminId).orElse(null);
            System.out.println(admin);
            if (admin == null) return null;
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
    public List<Dish> getDishes() {
        return dishRepo.findAll();
    }

    @Override
    public Menu getWithMI(Integer restaurantId) {
        return menuRepo.getWithMI(restaurantId);
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
