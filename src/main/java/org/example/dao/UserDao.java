package org.example.dao;

import org.example.entity.User;

import java.util.Optional;

public interface UserDao {

    void save(User user);

    Optional<User> findByUsername(String username);

}
