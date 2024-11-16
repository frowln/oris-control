package org.example.service;

import org.example.entity.User;

import java.util.Optional;

public interface UserService {

    boolean register(String username, String password);

    boolean login(String username, String password);

    Optional<User> findByUsername(String username);


}