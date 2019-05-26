package com.github.calve.to;

import com.github.calve.web.HasId;

public class DishTo implements HasId {

    private Integer id;

    private Double price;

    public DishTo() {
    }

    public DishTo(Integer id, Double price) {
        this.id = id;
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
                ", price=" + price +
                '}';
    }
}
