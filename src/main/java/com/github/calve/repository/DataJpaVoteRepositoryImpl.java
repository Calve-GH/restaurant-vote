package com.github.calve.repository;

import com.github.calve.model.*;
import com.github.calve.repository.datajpa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaVoteRepositoryImpl implements VoteRepository {

    @Autowired
    private CrudMenuRepository menuRepo;
    @Autowired
    private CrudVoteLogRepo voteLogRepo;
    @Autowired
    private CrudHistoryRepo historyRepo;
    @Autowired
    private CrudUserRepository userRepo;
    @Autowired
    private CrudRestaurantRepo restaurantRepo;

    @Override
    public List<Menu> getVoteList() {
        return menuRepo.findAllByDate(LocalDate.now());
    }

    @Override
    public List<HistoryItem> getVoteHistory() {
        return historyRepo.findAll();
    }

    @Override
    public List<HistoryItem> getVoteHistoryByRestaurant(Integer restaurantId) {
        return historyRepo.findByRestaurantId(restaurantId);
    }

    @Transactional
    @Override
    public void vote(Integer userId, Integer restaurantId) {
        User user = userRepo.getOne(userId);
        Restaurant restaurant = restaurantRepo.getOne(restaurantId);
        VoteLog voteLog = new VoteLog(user, restaurant);
        voteLogRepo.save(voteLog);
    }

    @Override
    public void unlockVote(Integer userId) {
        voteLogRepo.delete(userId);
    }

}
