package com.github.calve.service;

import com.github.calve.model.Menu;
import com.github.calve.model.MenuItem;
import com.github.calve.model.VoteLog;
import com.github.calve.repository.JpaUtil;
import com.github.calve.repository.MenuUtil;
import com.github.calve.repository.datajpa.CrudHistoryRepo;
import com.github.calve.repository.datajpa.CrudMenuItemRepository;
import com.github.calve.repository.datajpa.CrudMenuRepository;
import com.github.calve.repository.datajpa.CrudVoteLogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaSystemServiceImpl implements SystemService {

    @Autowired
    private CrudMenuRepository menuRepo;
    @Autowired
    private CrudMenuItemRepository menuItemRepo;
    @Autowired
    private CrudVoteLogRepo voteLogRepo;
    @Autowired
    private CrudHistoryRepo historyRepo;
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private JpaUtil jpaUtil;

    @Transactional
    @Override
    public void resetAndLogVoteSystem() {
        List<Menu> dailyVoteList = menuRepo.findAllByDate(LocalDate.now());
        historyRepo.saveAll(MenuUtil.convertMenuListToHistoryList(dailyVoteList));

        menuItemRepo.deleteAll();
        menuRepo.deleteAll();
        voteLogRepo.deleteAll();

        clearCache();
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<Menu> unsavedMenus = menuRepo.findByDateBefore(LocalDate.now());
        historyRepo.saveAll(MenuUtil.convertMenuListToHistoryList(unsavedMenus));
        menuRepo.deleteAll(unsavedMenus);
        voteLogRepo.deleteAllByDateBefore(LocalDate.now());
    }

    private void clearCache() {
        cacheManager.getCache("users").clear();
        cacheManager.getCache("dishes").clear();
        cacheManager.getCache("menus").clear();
        cacheManager.getCache("history").clear();
        cacheManager.getCache("restaurants").clear();
        if (jpaUtil != null) {
            jpaUtil.clear2ndLevelHibernateCache();
        }
    }
}
