package com.github.calve.repository;

import com.github.calve.model.User;
import com.github.calve.repository.datajpa.CrudRestaurantRepo;
import com.github.calve.repository.datajpa.CrudUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TempUserRepoImpl implements TempUserRepo {
    private static final Sort SORT_NAME_EMAIL = new Sort(Sort.Direction.ASC, "name", "email");

    @Autowired
    private CrudRestaurantRepo restaurantRepo;
    @Autowired
    private CrudUserRepository userRepo;

    @Override
    public User save(User user) {
        return userRepo.save(user);
    } //TODO SAVE ADMIN

    @Override
    public boolean delete(int id) {
        return userRepo.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return userRepo.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return userRepo.findAll(SORT_NAME_EMAIL);
    }

    @Override
    public void register() {
    }

    @Override
    public void login() {
    }
}
