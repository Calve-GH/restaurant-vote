package com.github.calve.service;

import com.github.calve.model.Menu;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service("systemService")
public class DataJpaSystemServiceImpl implements SystemService {

    private CrudMenuRepository menuRepo;
    private CrudVoteLogRepo voteLogRepo;
    private CrudHistoryRepo historyRepo;
    private JpaUtil jpaUtil;

    @Autowired
    public DataJpaSystemServiceImpl(CrudMenuRepository menuRepo, CrudVoteLogRepo voteLogRepo,
                                    CrudHistoryRepo historyRepo, JpaUtil jpaUtil) {
        this.menuRepo = menuRepo;
        this.voteLogRepo = voteLogRepo;
        this.historyRepo = historyRepo;
        this.jpaUtil = jpaUtil;
    }

    @Transactional
    @Override
    public void resetAndLogVoteSystem() {
        List<Menu> dailyVoteList = menuRepo.findAllByDate(LocalDate.now());
        historyRepo.saveAll(MenuUtil.convertMenuListToHistoryList(dailyVoteList));
        menuRepo.deleteAll(dailyVoteList);
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
        if (jpaUtil != null) {
            jpaUtil.clear2ndLevelHibernateCache();
        }
    }
}
