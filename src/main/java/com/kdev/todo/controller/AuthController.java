package com.kdev.todo.controller;

import com.kdev.todo.entity.JwtAuthResponse;
import com.kdev.todo.entity.LoginRequest;
import com.kdev.todo.entity.User;
import com.kdev.todo.service.impl.UserServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/todo/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserServiceImpl userService;

    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (user.getUsername() == null || user.getPassword() == null) {
            return new ResponseEntity<>("Username and Password are required", HttpStatus.BAD_REQUEST);
        }
        try {
            User registeredUser = userService.registerUser(user);
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Registration failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> loginUser(@RequestBody LoginRequest request) {
        try {
            // 1. Get the token from your service
            String token = userService.login(request);

            User user = userService.getUserByUsername(request.getUsername())
                    .orElseThrow(()-> new RuntimeException("User not found."));

            JwtAuthResponse response = new JwtAuthResponse();
            response.setAccessToken(token);
            response.setTokenType("Bearer");
            response.setId(user.getId());

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            // Improved error response: body is more helpful than just null headers
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}