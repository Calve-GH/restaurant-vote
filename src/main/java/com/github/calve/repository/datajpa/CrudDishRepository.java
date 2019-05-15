package com.github.calve.repository.datajpa;

import com.github.calve.model.Dish;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {

    @Transactional
    @CacheEvict(value = "dishes")
    Dish save(Dish dish);

    @Transactional
    void deleteById(Integer id);

    @Cacheable(value = "dishes")
    List<Dish> findAll();

    Optional<Dish> findByName(String name);
}
