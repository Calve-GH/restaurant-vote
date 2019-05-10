package com.github.calve.repository.datajpa;

import com.github.calve.model.HistoryItem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudHistoryRepo extends JpaRepository<HistoryItem, Integer> {

    @EntityGraph(attributePaths = {"restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT i FROM HistoryItem i ORDER BY i.date DESC")
    List<HistoryItem> findAll();

    @EntityGraph(attributePaths = {"restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    //@Query("SELECT i FROM HistoryItem i WHERE i.restaurant.id =:restaurant_id ORDER BY i.date DESC")
    List<HistoryItem> findByRestaurantId(@Param("restaurant_id") Integer restaurant_id);

    @Transactional
    HistoryItem save(HistoryItem item);

    @Transactional
    void deleteById(Integer id);
}
