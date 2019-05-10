package com.github.calve.service;


import com.github.calve.model.User;

import java.util.List;

public interface UserService {

//    User create(User user) throws Exception;

//    void delete(int id) throws Exception;

    User get(int id) throws Exception;

    User getByEmail(String email) throws Exception;

/*    void update(User user) throws Exception;

    void update(UserTo user) throws Exception;*/

    List<User> getAll();

//    void enable(int id, boolean enable);

}