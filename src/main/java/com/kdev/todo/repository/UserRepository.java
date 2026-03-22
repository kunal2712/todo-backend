package com.kdev.todo.repository;

import com.kdev.todo.entity.ToDo;
import com.kdev.todo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByMail(String mail);

    Optional<User> findByMailOrUsername(String mail , String username);


}
