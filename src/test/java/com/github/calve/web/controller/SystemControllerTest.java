package com.github.calve.web.controller;

import com.github.calve.repository.datajpa.CrudHistoryRepo;
import com.github.calve.repository.datajpa.CrudMenuItemRepository;
import com.github.calve.repository.datajpa.CrudRestaurantRepo;
import com.github.calve.repository.datajpa.CrudVoteLogRepo;
import com.github.calve.service.SystemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Transactional
public class SystemControllerTest extends AbstractControllerTest {

    @Autowired
    private SystemService systemService;
    @Autowired
    private CrudVoteLogRepo voteLogRepo;
    @Autowired
    private CrudHistoryRepo historyRepo;
    @Autowired
    private CrudRestaurantRepo restaurantRepo;
    @Autowired
    private CrudMenuItemRepository menuItemRepo;

    @Test
    void testResetAndLogSystem() {
        systemService.resetAndLogVoteSystem();
        assertEquals(historyRepo.findAll().size(), 5);
        assertEquals(voteLogRepo.findAll().size(), 0);
        assertEquals(menuItemRepo.findAll().size(), 8);
        assertEquals(voteLogRepo.findAll().size(), 0);
    }

    @Test
    void checkRestStateAfterReset() {
        restaurantRepo.clearRestaurantStates();
        restaurantRepo.findAll().forEach(r -> assertFalse(r.getMenuExist()));
    }

}
