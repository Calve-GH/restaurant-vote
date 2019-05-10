package com.github.calve.web.controller;

import com.github.calve.model.Dish;
import com.github.calve.model.Menu;
import com.github.calve.repository.MenuRepository;
import com.github.calve.to.DishTo;
import com.github.calve.web.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = MenuRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuRestController {

    public static final String REST_URL = "/rest/admin/menu";

    @Autowired
    private MenuRepository menuRepository;

    @GetMapping
    Menu getDailyMenu() {
        return menuRepository.getWithMI(SecurityUtil.get().getUserTo().getRestaurant().getId());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Menu> saveDailyMenu(@RequestBody List<DishTo> items) {
        Set<DishTo> itemsSet = new HashSet<>(items);
        Menu createdMenu = menuRepository.saveDailyMenu(itemsSet, SecurityUtil.authUserId());
        URI newMenuUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(REST_URL).buildAndExpand().toUri();
        return ResponseEntity.created(newMenuUri).body(createdMenu);
    }

    @GetMapping("/dishes")
    List<Dish> getDishes() {
        return menuRepository.getDishes();
    }
}
