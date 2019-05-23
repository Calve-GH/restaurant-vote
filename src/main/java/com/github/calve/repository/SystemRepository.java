package com.github.calve.repository;

import com.github.calve.model.MenuItem;
import com.github.calve.model.VoteLog;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.List;

public interface SystemRepository {

    void resetAndLogVoteSystem();

    List<MenuItem> getMenuItems();

    List<VoteLog> getVoteLogs();

    void onApplicationEvent(ContextRefreshedEvent event);
}
