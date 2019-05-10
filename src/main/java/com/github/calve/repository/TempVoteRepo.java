package com.github.calve.repository;

import com.github.calve.model.MenuItem;
import com.github.calve.model.VoteLog;

import java.util.List;

public interface TempVoteRepo {

    List<VoteLog> getAllLogs();

    List<MenuItem> getMenuItems();

    VoteLog findByUserId(Integer userId);
}
