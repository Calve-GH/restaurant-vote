package com.github.calve.to;

import com.github.calve.model.MenuItem;
import com.github.calve.model.Restaurant;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class MenuTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private LocalDate date;
    @NotNull
    private Restaurant restaurant;
    @Size(min = 2, max = 5)
    private List<MenuItem> menuItems;

    private Integer voteCount = 0;

    public MenuTo() {
    }

    public MenuTo(@NotNull LocalDate date, @NotNull Restaurant restaurant, @Size(min = 2, max = 5) List<MenuItem> menuItems) {
        this.id = null;
        this.date = date;
        this.restaurant = restaurant;
        this.menuItems = menuItems;
    }

    public MenuTo(Integer id, @NotNull LocalDate date, @NotNull Restaurant restaurant, @Size(min = 2, max = 5) List<MenuItem> menuItems, Integer voteCount) {
        this.id = id;
        this.date = date;
        this.restaurant = restaurant;
        this.menuItems = menuItems;
        this.voteCount = voteCount;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}
