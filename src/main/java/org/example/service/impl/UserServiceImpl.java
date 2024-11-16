package org.example.service.impl;

import org.example.dao.UserDao;
import org.example.dao.impl.LoginAttemptDaoImpl;
import org.example.dao.impl.UserDaoImpl;
import org.example.entity.User;
import org.example.service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = UserDaoImpl.getInstance();
    private final LoginAttemptDaoImpl loginAttemptDao = LoginAttemptDaoImpl.getInstance();
    private static final UserServiceImpl INSTANCE = new UserServiceImpl();

    public static UserServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean register(String username, String password) {
        if (userDao.findByUsername(username).isPresent()) {
            return false;
        }
        userDao.save(new User(username, password));
        return true;
    }

    @Override
    public boolean login(String username, String password) {
        Optional<User> user = userDao.findByUsername(username);
        boolean success = user.isPresent() && user.get().getPassword().equals(password);

        loginAttemptDao.saveLoginAttempt(username, success);

        return success;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}