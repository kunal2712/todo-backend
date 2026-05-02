package com.kdev.todo.service;

import com.kdev.todo.entity.LoginRequest;
import com.kdev.todo.entity.User;
import java.util.Optional;

public interface UserService {

    User registerUser(User user);

    Optional<User> getUserById(long id);

    Optional<User> getUserByUsername(String username);

    // Add this to handle the logic for JWT generation
    String login(LoginRequest loginRequest);
}