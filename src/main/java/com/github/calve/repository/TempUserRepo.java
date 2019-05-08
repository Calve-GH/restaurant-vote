package com.github.calve.repository;

import com.github.calve.model.User;

import java.util.List;

public interface TempUserRepo {

    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    List<User> getAll();

    void register();

    void login();

}
