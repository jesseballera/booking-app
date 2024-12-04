package com.purplemango.gms.controller;

import com.purplemango.gms.models.iam.AddUser;
import com.purplemango.gms.models.iam.User;
import com.purplemango.gms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public ResponseEntity<Collection<User>> viewAllUsers() {
        return ResponseEntity.ok(userService.viewAllUsers());
    }

    @GetMapping("/name/{username}")
    public ResponseEntity<User> viewUserByUsername(@PathVariable("username") String username) {
        return ResponseEntity.ok(userService.viewUserByUsername(username));
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<User> viewUserById(@PathVariable("user-id") String userId) {
        return ResponseEntity.ok(userService.viewUserById(userId));
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody AddUser user) {
        userService.createUser(user);
        return ResponseEntity.ok("User created successfully");
    }
}
