package com.purplemango.gms.controller;

import com.purplemango.gms.models.iam.AddUser;
import com.purplemango.gms.models.iam.User;
import com.purplemango.gms.service.TenantService;
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
    private final TenantService tenantService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, TenantService tenantService,
                          PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.tenantService = tenantService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/{tenant-id}")
    public ResponseEntity<Collection<User>> viewAllUsers(@PathVariable("tenant-id") String tenantId) {
        if (!tenantService.existByTenantId(tenantId))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userService.viewAllUsers(tenantId));
    }

    @GetMapping("/{tenant-id}/find-by-username")
    public ResponseEntity<User> viewUserByUsername(@PathVariable("tenant-id") String tenantId, @RequestParam("username") String username) {
        if (!tenantService.existByTenantId(tenantId))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userService.viewUserByUsername(tenantId, username));
    }

    @GetMapping("/{tenant-id}/find-by-id")
    public ResponseEntity<User> viewUserById(@PathVariable("tenant-id") String tenantId, @RequestParam("user-id") String userId) {
        if (!tenantService.existByTenantId(tenantId))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userService.viewUserById(tenantId, userId));
    }

    @PostMapping("/{tenant-id}")
    public ResponseEntity<String> createUser(@PathVariable("tenant-id") String tenantId, @RequestBody AddUser user) {
        if (!tenantService.existByTenantId(tenantId))
            return ResponseEntity.notFound().build();
        userService.createUser(tenantId, user);
        return ResponseEntity.ok("User created successfully");
    }
}
