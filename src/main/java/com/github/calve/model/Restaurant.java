package com.github.calve.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "restaurant")
public class Restaurant extends AbstractNamedEntity {

    @Column(name = "menu_exist", nullable = false, columnDefinition = "bool default false")
    private Boolean menu_exist = false;

    public Restaurant(Integer id, String name , Boolean menu_exist) {
        super(id, name);
        this.menu_exist = menu_exist;
    }

    public Restaurant() {
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Restaurant(@NotNull String name) {
        this.name = name;
    }

    public Boolean getMenuExist() {
        return menu_exist;
    }

    public void setMenuExist(Boolean menu_exist) {
        this.menu_exist = menu_exist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", menu='" + menu_exist + '\'' +
                '}';
    }
}
