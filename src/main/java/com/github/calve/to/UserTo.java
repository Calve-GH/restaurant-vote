package com.github.calve.to;

import com.github.calve.model.Restaurant;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class UserTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @Size(min = 5, max = 32, message = "length must between 5 and 32 characters")
    private String password;

    private Restaurant restaurant = null;

    private String newRestaurantName;

    public UserTo() {
    }

    public UserTo(Integer id, String name, String email, String password, Restaurant restaurant) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
        this.restaurant = restaurant;
    }

    public UserTo(Integer id, String name, String email, String password, String restaurant) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
        this.newRestaurantName = restaurant;
    }

    public boolean isRestaurantEmpty() {
        if (newRestaurantName == null) return true;
        newRestaurantName = newRestaurantName.trim();
        return newRestaurantName.length() < 2;
    }

    public String getNewRestaurantName() {
        return newRestaurantName;
    }

    public void setNewRestaurantName(String newRestaurantName) {
        this.newRestaurantName = newRestaurantName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
