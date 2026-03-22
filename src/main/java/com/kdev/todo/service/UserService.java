package com.kdev.todo.service;

import com.kdev.todo.entity.ToDo;
import com.kdev.todo.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
public interface UserService {

    User registerUser(User user);
    Optional<User> getUserById(long id);

    Optional<User> getUserByUsername(String username);


}
