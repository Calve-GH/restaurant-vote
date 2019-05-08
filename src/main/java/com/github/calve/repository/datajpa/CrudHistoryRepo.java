package com.github.calve.repository.datajpa;

import com.github.calve.model.HistoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudHistoryRepo extends JpaRepository<HistoryItem, Integer> {

    @Query("SELECT i FROM HistoryItem i ORDER BY i.date DESC")
    List<HistoryItem> findAll();

    //@Query("SELECT i FROM HistoryItem i WHERE i.restaurant.id = restaurant_id ORDER BY i.date DESC")
    List<HistoryItem> findByRestaurantId(Integer restaurant_id);

    @Transactional
    HistoryItem save(HistoryItem item);

    @Transactional
    void deleteById(Integer id);
}
