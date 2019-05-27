package com.github.calve.web.controller;

import com.github.calve.model.Dish;
import com.github.calve.model.Menu;
import com.github.calve.model.Restaurant;
import com.github.calve.repository.datajpa.CrudDishRepository;
import com.github.calve.repository.datajpa.CrudMenuRepository;
import com.github.calve.repository.datajpa.CrudRestaurantRepo;
import com.github.calve.to.MenuTo;
import com.github.calve.util.exception.StoreEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.github.calve.repository.MenuUtil.createFromTo;
import static com.github.calve.repository.MenuUtil.createNewFromTo;
import static com.github.calve.util.ExceptionUtil.*;
import static com.github.calve.util.ValidationUtil.*;

@RestController
@RequestMapping(value = MenuRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@PropertySource("classpath:restaurant-vote.properties")
public class MenuRestController {

    public static final String REST_URL = "/rest/admin";
    @Value("${dish.list.min.size}")
    private Integer minDishes;//TODO
    @Value("${dish.list.max.size}")
    private Integer maxDishes;

    @Autowired
    private CrudMenuRepository menuRepo;
    @Autowired
    private CrudDishRepository dishRepo;
    @Autowired
    private CrudRestaurantRepo restaurantRepo;

    @GetMapping("/menu")
    public List<Menu> getMenus(@RequestParam(required = false) LocalDate date,
                               @RequestParam(required = false) Integer restaurantId) {

        if (date == null && restaurantId == null) {
            return menuRepo.findAll();
        }
        if (date == null) {
            return menuRepo.findAllByRestaurantId(restaurantId);
        }
        if (restaurantId == null) {
            return menuRepo.findAllByDate(date);
        }
        return Arrays.asList(menuRepo.findByDateAndRestaurantId(date, restaurantId));
    }

    @PostMapping(value = "/menu", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Menu> saveMenu(@Valid @RequestBody MenuTo menuTo) {
        Menu newMenu = createNewFromTo(menuTo);
        checkNew(newMenu);
//        try {
            newMenu = menuRepo.save(newMenu);
/*        } catch (Exception e) {
            Throwable t = getCause(e);
            if (t.getMessage().contains(MENU_UNIQUE_RESTAURANT_DATE_IDX))
                throw new StoreEntityException(MENU_UNIQUE_RESTAURANT_DATE_IDX_MSG);
        }*/
        URI newMenuUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(REST_URL + "/menu/"
                + newMenu.getId()).buildAndExpand().toUri();
        return ResponseEntity.created(newMenuUri).body(newMenu);
    }

    @PutMapping(value = "/menu/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateMenu(@Valid @RequestBody MenuTo menuTo, @PathVariable int id) {
        assureIdConsistent(menuTo, id);
        try {
            menuRepo.save(createFromTo(menuTo));
        } catch (Exception e) {
            Throwable t = getCause(e);
            if (t.getMessage().contains(MENU_UNIQUE_RESTAURANT_DATE_IDX))
                throw new StoreEntityException(MENU_UNIQUE_RESTAURANT_DATE_IDX_MSG);
        }
    }

    @DeleteMapping("/menu/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMenu(@PathVariable Integer id) {
        checkNotFoundWithId(menuRepo.delete(id) != 0, id);
    }

    @GetMapping("/dish")
    public List<Dish> getDishes() {
        return dishRepo.findAll();
    }

    @PostMapping("/dish")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void saveDish(@RequestBody Dish dish) {
        try {
            dishRepo.save(dish);
        } catch (Exception e) {
            Throwable t = getCause(e);
            if (t.getMessage().contains(DISH_UNIQUE_NAME_IDX))
                throw new StoreEntityException(DISH_UNIQUE_NAME_IDX_MSG);
        }
    }

    @DeleteMapping("/dish/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDish(@PathVariable Integer id) {
        checkNotFoundWithId(dishRepo.delete(id) != 0, id);
    }

    @GetMapping("/restaurant")
    public List<Restaurant> getRestaurants() {
        return restaurantRepo.findAll();
    }

    @PostMapping("/restaurant")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void saveRestaurant(@RequestBody Restaurant restaurant) {
        try {
            restaurantRepo.save(restaurant);
        } catch (Exception e) {
            Throwable t = getCause(e);
            if (t.getMessage().contains(RESTAURANT_UNIQUE_NAME_IDX))
                throw new StoreEntityException(RESTAURANT_UNIQUE_NAME_IDX_MSG);
        }
    }

    @DeleteMapping("/restaurant/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRestaurant(@PathVariable Integer id) {
        checkNotFoundWithId(restaurantRepo.delete(id) != 0, id);
    }
}
