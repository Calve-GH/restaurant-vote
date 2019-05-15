package com.github.calve.web.controller;

import com.github.calve.model.Dish;
import com.github.calve.model.Menu;
import com.github.calve.model.MenuItem;
import com.github.calve.model.User;
import com.github.calve.repository.datajpa.CrudDishRepository;
import com.github.calve.repository.datajpa.CrudMenuRepository;
import com.github.calve.repository.datajpa.CrudUserRepository;
import com.github.calve.to.DishTo;
import com.github.calve.util.ValidationUtil;
import com.github.calve.util.exception.IllegalRequestDataException;
import com.github.calve.web.SecurityUtil;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping(value = MenuRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@PropertySource("classpath:restaurant-vote.properties")
public class MenuRestController {

    public static final String REST_URL = "/rest/admin/menu";
    @Value("${dish.list.min.size}")
    private Integer minDishes;
    @Value("${dish.list.max.size}")
    private Integer maxDishes;

    @Autowired
    private CrudMenuRepository menuRepo;
    @Autowired
    private CrudDishRepository dishRepo;
    @Autowired
    private CrudUserRepository userRepo;

    @GetMapping
    Menu getDailyMenu() {
        return menuRepo.getWithMI(SecurityUtil.get().getUserTo().getRestaurant().getId());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Menu> saveDailyMenu(@RequestBody List<DishTo> items) {
        Set<DishTo> itemsSet = new HashSet<>(items);
        Menu createdMenu = saveDailyMenu(itemsSet, SecurityUtil.authUserId());
        URI newMenuUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(REST_URL).buildAndExpand().toUri();
        return ResponseEntity.created(newMenuUri).body(createdMenu);
    }

    @GetMapping("/dishes")
    public List<Dish> getDishes() {
        return dishRepo.findAll();
    }

    @Transactional
    public Menu saveDailyMenu(Set<DishTo> dishes, Integer adminId) {
        if (dishes.size() >= minDishes && dishes.size() <= maxDishes) {
            User admin = userRepo.findById(adminId).orElse(null);
            ValidationUtil.checkNotFound(admin, "current id");
            menuRepo.deleteByRestaurantId(Objects.requireNonNull(admin).getRestaurant().getId());
            Menu menu = new Menu(LocalDate.now(), admin.getRestaurant());
            menu = menuRepo.save(menu);
            Set<MenuItem> items = parseMenuItems(dishes, menu);
            menu.setItems(items);
            menuRepo.save(menu);
            return menu;
        }
        throw new IllegalRequestDataException(getOutOfRangeString(minDishes, maxDishes));
    }

    private String getOutOfRangeString(Integer minDishes, Integer maxDishes) {
        StringBuilder sb = new StringBuilder("Number of menu dishes out of range [");
        sb.append(minDishes);
        sb.append(" - ");
        sb.append(maxDishes);
        sb.append("]");
        return sb.toString();
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
