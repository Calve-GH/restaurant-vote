package com.github.calve.service;

import com.github.calve.model.MenuItem;
import com.github.calve.model.VoteLog;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.List;

public interface SystemService {

    void resetAndLogVoteSystem();

    void onApplicationEvent(ContextRefreshedEvent event);
}
