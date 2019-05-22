package com.github.calve.repository.datajpa;

import com.github.calve.model.Restaurant;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudRestaurantRepo extends JpaRepository<Restaurant, Integer> {

    @Override
    Optional<Restaurant> findById(Integer id);

    @CacheEvict(value = "restaurants", allEntries = true)
    @Transactional
    void deleteById(Integer id);

    @CacheEvict(value = "restaurants", allEntries = true)
    @Transactional
    Restaurant save(Restaurant restaurant);

    @Cacheable("restaurants")
    @Query("SELECT r FROM Restaurant r ORDER BY r.name DESC")
    List<Restaurant> getAll();

    @CacheEvict(value = "restaurants", allEntries = true)
    @Transactional
    @Modifying
    @Query("UPDATE Restaurant r SET r.menu_exist=false")
    void clearRestaurantStates();
}
