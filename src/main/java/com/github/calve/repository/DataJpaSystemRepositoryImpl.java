package com.github.calve.repository;

import com.github.calve.model.Menu;
import com.github.calve.model.MenuItem;
import com.github.calve.model.VoteLog;
import com.github.calve.repository.datajpa.CrudHistoryRepo;
import com.github.calve.repository.datajpa.CrudMenuItemRepository;
import com.github.calve.repository.datajpa.CrudMenuRepository;
import com.github.calve.repository.datajpa.CrudVoteLogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaSystemRepositoryImpl implements SystemRepository {

    @Autowired
    private CrudMenuRepository menuRepo;
    @Autowired
    private CrudMenuItemRepository menuItemRepo;
    @Autowired
    private CrudVoteLogRepo voteLogRepo;
    @Autowired
    private CrudHistoryRepo historyRepo;
/*    @Autowired
    private CrudUserRepository userRepo;
    @Autowired
    private CrudRestaurantRepo restaurantRepo;*/

    @Transactional
    @Override
    public void resetAndLogVoteSystem() {
        List<Menu> dailyVoteList = menuRepo.findAllByDate(LocalDate.now()); //IT WORKS IN ~23.45 like example
        historyRepo.saveAll(JpaUtil.convertMenuListToHistoryList(dailyVoteList));

        menuItemRepo.deleteAll();
        menuRepo.deleteAll();
        voteLogRepo.deleteAll();

        //Clear all caches     //TODO      сбросить все кеши
    }

    @Override
    public List<MenuItem> getMenuItems() {
        return menuItemRepo.findAll();
    }

    @Override
    public List<VoteLog> getVoteLogs() {
        return voteLogRepo.findAll();
    }
}
