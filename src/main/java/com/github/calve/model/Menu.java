package com.github.calve.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "menu")
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

    //@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    //@CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    //@Column(name = "role")
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "menu")
    //@BatchSize(size = 200)
    private Set<MenuItem> items = new HashSet<>(0);


    @Column(name = "vote_count", nullable = false)
    private Integer voteCount = 0;

    public Menu(Integer id, @NotNull LocalDate date, Restaurant restaurant) {
        super(id);
        this.date = date;
        this.restaurant = restaurant;
    }

    public Menu(@NotNull LocalDate date, Restaurant restaurant) {
        this.date = date;
        this.restaurant = restaurant;
    }

    public Menu(@NotNull LocalDate date, Restaurant restaurant, Set<MenuItem> dishes) {
        this.date = date;
        this.restaurant = restaurant;
        this.items = dishes;
    }

    public Menu() {
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

/*    public void addMenuItemToMenu(MenuItem item) {
        item.setMenu(this);
        this.items.add(item);
    }*/

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", date=" + date +
                ", restaurant=" + restaurant +
                ", items=" + items +
                ", voteCount=" + voteCount +
                '}';
    }
}
