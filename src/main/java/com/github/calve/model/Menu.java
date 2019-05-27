package com.github.calve.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "menu", uniqueConstraints ={@UniqueConstraint(columnNames = {"date", "restaurant_id"}, name = "menu_unique_restaurant_date_idx")})
public class Menu extends AbstractBaseEntity {
    public static final String DATE_PATTERN = "yyyy-MM-dd";

    @Column(name = "date", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DATE_PATTERN)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "menu", orphanRemoval = true)
    @JsonIgnoreProperties(value = "menu", allowSetters = true)
    private Set<MenuItem> items = new HashSet<>(0);

    @Column(name = "vote_count", nullable = false)
    private Integer voteCount = 0;

    public Menu() {
    }

    public Menu(Integer id, @NotNull LocalDate date, Restaurant restaurant, Integer voteCount) {
        super(id);
        this.date = date;
        this.restaurant = restaurant;
        if (voteCount != null)
            this.voteCount = voteCount;
    }

    public Menu(LocalDate date, Restaurant restaurant) {
        this.date = date;
        this.restaurant = restaurant;
    }

    public Menu(LocalDate date, Restaurant restaurant, Set<MenuItem> dishes) {
        this.date = date;
        this.restaurant = restaurant;
        this.items = dishes;
    }

    public void addMenuItem(MenuItem menuItem) {
        this.items.add(menuItem);
        menuItem.setMenu(this);
    }
    public void removeMenuItem(MenuItem menuItem) {
        this.items.remove(menuItem);
        menuItem.setMenu(null);
    }

    public void vote() {
        voteCount++;
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


    public Set<MenuItem> getItems() {
        return items;
    }

    public void setItems(Set<MenuItem> items) {
        this.items = items;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "date=" + date +
                ", restaurant=" + restaurant +
                ", items=" + items +
                ", voteCount=" + voteCount +
                ", id=" + id +
                '}';
    }
}
