package com.github.calve.repository.datajpa;

import com.github.calve.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CrudMenuItemRepository extends JpaRepository<MenuItem, Integer> {
    @Transactional
    MenuItem save(MenuItem item);

    @Transactional
    void delete(MenuItem item);

    List<MenuItem> findByMenuId(Integer id);

    List<MenuItem> findAll();
}
