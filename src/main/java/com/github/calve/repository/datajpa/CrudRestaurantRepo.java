package com.github.calve.repository.datajpa;

import com.github.calve.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudRestaurantRepo extends JpaRepository<Restaurant, Integer> {

    @Override
    Optional<Restaurant> findById(Integer id);

    @Transactional
    void deleteById(Integer id);

    @Transactional
    Restaurant save(Restaurant restaurant);

    @Query("SELECT r FROM Restaurant r ORDER BY r.name DESC")
    List<Restaurant> getAll();
}
