package com.github.calve.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "menu_item")
public class MenuItem extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties(value = "items", allowSetters = true)
    private Menu menu;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dish_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Dish dish;

    @Column(name = "price", nullable = false)
    @NotNull
    private Double price;

    public MenuItem(Integer id, Menu menu, Dish dish, @NotNull Double price) {
        super(id);
        this.menu = menu;
        this.dish = dish;
        this.price = price;
    }

    public MenuItem(Dish dish, @NotNull Double price) {
        this.dish = dish;
        this.price = price;
    }

    public MenuItem(Menu menu, Dish dish, @NotNull Double price) {
        this.menu = menu;
        this.dish = dish;
        this.price = price;
    }

    public MenuItem() {
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setId(Menu menu) {
        this.menu = menu;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "MenuItem{" + "id=" + id +
                ", dish=" + dish.getName() +
                ", price=" + price +
                '}';
    }
}
