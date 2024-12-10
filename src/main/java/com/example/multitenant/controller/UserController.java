package com.example.multitenant.controller;

import com.example.multitenant.service.UserService;
import com.example.multitenant.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers(@RequestHeader("X-Tenant-ID") String tenantId) {
        return userService.getUsersForTenant(tenantId);
    }

    @PostMapping
    public User addUser(@RequestHeader("X-Tenant-ID") String tenantId, @RequestBody User user) {
        return userService.addUserForTenant(tenantId, user);
    }

    @PutMapping
    public User updateUser(@RequestHeader("X-Tenant-ID") String tenantId, @RequestBody User user) {
        return userService.updateUserForTenant(tenantId, user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@RequestHeader("X-Tenant-ID") String tenantId, @PathVariable Long userId) {
        userService.deleteUserForTenant(tenantId, userId);
    }
}
