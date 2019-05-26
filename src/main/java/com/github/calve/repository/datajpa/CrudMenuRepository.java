package com.github.calve.repository.datajpa;

import com.github.calve.model.Menu;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {

    @CacheEvict(value = "menus", allEntries = true)
    @Transactional
    @Query("DELETE FROM Menu m WHERE m.id=:id")
    int delete(Integer id);


    @CacheEvict(value = {"menus", "restaurants"})
    @Override
    @Transactional
    Menu save(Menu item);

    Optional<Menu> findById(Integer id);

    @Transactional
    @Override
    void deleteAll();

    @Transactional
    @Modifying
    @Query("DELETE FROM Menu m WHERE m.restaurant.id=:restId")
    void deleteByRestaurantId(@Param("restId") Integer restaurantId);//TODO

    @EntityGraph(attributePaths = {"restaurant", "items"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Menu m WHERE m.restaurant.id=?1")
    Menu getWithMenuItems(Integer id);

//----------------------
    @Transactional
    @EntityGraph(attributePaths = {"restaurant", "items"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Menu> findByDateBefore(LocalDate date);

    @Override
    @EntityGraph(attributePaths = {"restaurant", "items"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Menu m ORDER BY m.date DESC")
    List<Menu> findAll();

    @Cacheable("menus")
    @EntityGraph(attributePaths = {"restaurant", "items"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Menu m WHERE m.date=?1")
    List<Menu> findAllByDate(LocalDate date);

    @EntityGraph(attributePaths = {"restaurant", "items"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Menu m WHERE m.restaurant.id=:restId ORDER BY m.date DESC")
    List<Menu> findAllByRestaurantId(@Param("restId") int restaurantId);

    @EntityGraph(attributePaths = {"restaurant", "items"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Menu m WHERE m.date=?1 AND m.restaurant.id=?2")
    Menu findByDateAndRestaurantId(LocalDate date, Integer restaurantId);
}