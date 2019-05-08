package com.github.calve.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "history")
public class HistoryItem extends AbstractBaseEntity {

    @Column(name = "date", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDate date;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    @Column(name = "data", nullable = false)
    @NotBlank
    private String data;

    @Column(name = "count", nullable = false, columnDefinition = "Integer default 0")
    @NotNull
    private Integer count;

    public HistoryItem() {
    }

    public HistoryItem(Integer id, @NotNull LocalDate date, @NotNull Restaurant restaurant, @NotBlank String data, @NotNull Integer count) {
        super(id);
        this.date = date;
        this.restaurant = restaurant;
        this.data = data;
        this.count = count;
    }

    public HistoryItem(@NotNull LocalDate date, @NotNull Restaurant restaurant, @NotBlank String data, @NotNull Integer count) {
        this.date = date;
        this.restaurant = restaurant;
        this.data = data;
        this.count = count;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "HistoryItem{" +
                "date=" + date +
                ", restaurant=" + restaurant +
                ", data='" + data + '\'' +
                ", count=" + count +
                '}';
    }
}
