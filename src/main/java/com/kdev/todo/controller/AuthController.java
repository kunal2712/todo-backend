package com.kdev.todo.controller;


import com.kdev.todo.entity.LoginRequest;
import com.kdev.todo.entity.User;
import com.kdev.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/todo/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private final UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthController(UserService userService ,  PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        if(user.getUsername() == null || user.getPassword() == null || user.getMail() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User registeredUser = userService.registerUser(user);
        return new ResponseEntity<>(registeredUser  , HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        return userService.getUserByUsername(request.getUsername())
                .map(user -> {
                    if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                        return new ResponseEntity<>(user, HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
                    }
                })
                .orElse(new ResponseEntity<>("User not found", HttpStatus.UNAUTHORIZED));
    }


}
