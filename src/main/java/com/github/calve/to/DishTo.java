package com.github.calve.to;

import com.github.calve.web.HasId;

public class DishTo implements HasId {

    private Integer id;

    private String name;

    private Double price;

    public DishTo() {
    }

    public DishTo(String name, Double price) {
        this.id = null;
        this.name = name;
        this.price = price;
    }

    public DishTo(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "DishTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
