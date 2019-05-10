package com.github.calve.repository.datajpa;

import com.github.calve.model.Menu;
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

    @Transactional
    void deleteById(Integer id);

    @Override
    @Transactional
    Menu save(Menu item);

    @Override
    List<Menu> findAll();

    Optional<Menu> findById(Integer id);

    @Transactional
    @Override
    void deleteAll();

    @Transactional
    @Modifying
    @Query("DELETE FROM Menu m WHERE m.restaurant.id=:restId")
    void deleteByRestaurantId(@Param("restId") Integer restaurantId);

    @Query("SELECT m FROM Menu m WHERE m.restaurant.id=:restId ORDER BY m.date DESC")
    List<Menu> getAllByRestaurant(@Param("restId") int restaurantId);

    Menu getMenuByDateAndRestaurantId(LocalDate date, Integer restaurantId);

    @EntityGraph(attributePaths = {"restaurant", "items"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Menu m WHERE m.date=?1")
    List<Menu> findAllByDate(LocalDate date);

/*    @EntityGraph(attributePaths = {"meals"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT u FROM User u WHERE u.id=?1")
    User getWithMeals(int id);*/

    @EntityGraph(attributePaths = {"restaurant", "items"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Menu m WHERE m.restaurant.id=?1")
    Menu getWithMI(Integer id);


    /*@SuppressWarnings("JpaQlInspection")
    @Query("SELECT m from Meal m WHERE m.user.id=:userId AND m.dateTime BETWEEN :startDate AND :endDate ORDER BY m.dateTime DESC")
    List<Meal> getBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("userId") int userId);
*/
/*    @Query("SELECT m FROM Meal m JOIN FETCH m.user WHERE m.id = ?1 and m.user.id = ?2")
    Meal getWithUser(int id, int userId);*/
}