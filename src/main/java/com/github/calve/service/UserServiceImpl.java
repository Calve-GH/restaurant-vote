package com.github.calve.service;

import com.github.calve.model.User;
import com.github.calve.repository.datajpa.CrudUserRepository;
import com.github.calve.web.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.github.calve.UserUtil.prepareToSave;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final CrudUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(CrudUserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    @CacheEvict(value = "users", allEntries = true)
    @Override
    public User create(User user) {
        try {
            return repository.save(prepareToSave(user, passwordEncoder));
        } catch (Exception e) {
            e.printStackTrace();
/*            Throwable t = getCause(e);
            if (t.getMessage().contains(UNIQUE_EMAIL_IDX))
                throw new Exception(UNIQUE_EMAIL_IDX_MSG);*/
        }
        return null;
    }

    @Override
    public User get(int id) throws Exception {
        return null;
    }

    @Override
    public User getByEmail(String email) throws Exception {
        Assert.notNull(email, "email must not be null");
        return repository.getByEmail(email);
    }

    @Cacheable("users")
    @Override
    public List<User> getAll() {
        return repository.findAll();
    }
    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }
}