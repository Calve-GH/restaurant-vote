package com.github.calve.repository;

import com.github.calve.model.*;
import com.github.calve.repository.datajpa.CrudHistoryRepo;
import com.github.calve.repository.datajpa.CrudMenuItemRepository;
import com.github.calve.repository.datajpa.CrudMenuRepository;
import com.github.calve.repository.datajpa.CrudVoteLogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class TempVoteRepoImpl implements TempVoteRepo {

    @Autowired
    private CrudMenuRepository menuRepo;
    @Autowired
    private CrudVoteLogRepo voteLogRepo;
    @Autowired
    private CrudHistoryRepo historyRepo;
    @Autowired
    private CrudMenuItemRepository menuItemRepo;

    @Override
    public List<VoteLog> getAllLogs() {
        return voteLogRepo.findAll();
    }

    @Override
    public List<MenuItem> getMenuItems() {
        return menuItemRepo.findAll();
    }
//TODO handle NULL
    @Override
    public VoteLog findByUserId(Integer userId) {
        return voteLogRepo.findByUserId(userId).orElse(null);
    }
}
