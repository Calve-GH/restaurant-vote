package com.github.calve.web;

import com.github.calve.UserUtil;
import com.github.calve.model.User;
import com.github.calve.to.UserTo;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    private UserTo userTo;
    private User user;

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, user.getRoles());
        this.userTo = UserUtil.asTo(user);
        this.user = user;
    }

    public int getId() {
        return userTo.getId();
    }

    public void update(UserTo newTo) {
        userTo = newTo;
    }

    public UserTo getUserTo() {
        return userTo;
    }

    public User getUser() {return user;}

    @Override
    public String toString() {
        return userTo.toString();
    }
}