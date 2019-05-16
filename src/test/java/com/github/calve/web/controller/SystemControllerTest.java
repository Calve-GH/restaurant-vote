package com.github.calve.web.controller;

import com.github.calve.repository.SystemRepository;
import com.github.calve.repository.datajpa.CrudHistoryRepo;
import com.github.calve.repository.datajpa.CrudVoteLogRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SystemControllerTest extends AbstractControllerTest {

    @Autowired
    private SystemRepository systemRepository;
    @Autowired
    private CrudVoteLogRepo voteLogRepo;
    @Autowired
    private CrudHistoryRepo historyRepo;

    @Test
    void testResetAndLogSystem() {
        systemRepository.resetAndLogVoteSystem();
        assertEquals(historyRepo.findAll().size(), 4);
        assertEquals(voteLogRepo.findAll().size(), 0);
        assertEquals(systemRepository.getMenuItems().size(), 0);
        assertEquals(systemRepository.getVoteLogs().size(), 0);
    }
}
