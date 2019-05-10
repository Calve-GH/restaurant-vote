package com.github.calve.service;

import com.github.calve.model.User;
import com.github.calve.repository.TempUserRepo;
import com.github.calve.web.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final TempUserRepo repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(TempUserRepo repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


/*    @Override
    public User create(User user) throws Exception {
        Assert.notNull(user, "user must not be null");
        try {
            return repository.save(prepareToSave(user, passwordEncoder));
        } catch (Exception e) {
            Throwable t = getCause(e);
            if (t.getMessage().contains(UNIQUE_EMAIL_IDX))
                throw new Exception(UNIQUE_EMAIL_IDX_MSG);
        }
        return null;
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }*/

    @Override
    public User get(int id) throws Exception {
        return null;
    }

    @Override
    public User getByEmail(String email) throws Exception {
        Assert.notNull(email, "email must not be null");
        return null;
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }
/*
//    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void update(User user) throws StoreEntityException {
        Assert.notNull(user, "user must not be null");
        try {
            checkNotFoundWithId(repository.save(prepareToSave(user, passwordEncoder)), user.getId());
        } catch (Exception e) {
            Throwable t = getCause(e);
            if (t.getMessage().contains(UNIQUE_EMAIL_IDX))
                throw new StoreEntityException(UNIQUE_EMAIL_IDX_MSG);
        }
    }

    @Transactional
    @Override
    public void update(UserTo userTo) throws StoreEntityException {
        User user = updateFromTo(get(userTo.getId()), userTo);
        repository.save(prepareToSave(user, passwordEncoder));

    }

    @Override
    @Transactional
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
        repository.save(user);  // !! need only for JDBC implementation
    }*/

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }
}