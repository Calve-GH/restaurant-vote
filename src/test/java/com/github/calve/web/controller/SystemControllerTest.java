package com.github.calve.web.controller;

import com.github.calve.repository.SystemRepository;
import com.github.calve.repository.VoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SystemControllerTest extends AbstractControllerTest {

    @Autowired
    private SystemRepository systemRepository;
    @Autowired
    private VoteRepository voteRepository;

    @Test
    void resetAndLogSystem() {
        systemRepository.resetAndLogVoteSystem();
        assertEquals(voteRepository.getVoteHistory().size(), 5);
        assertEquals(voteRepository.getVoteList().size(), 0);
        assertEquals(systemRepository.getMenuItems().size(), 0);
        assertEquals(systemRepository.getVoteLogs().size(), 0);
    }
}
