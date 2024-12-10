package com.example.multitenant.service;

import com.example.multitenant.model.User;
import com.example.multitenant.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsersForTenant(String tenantId) {
        return userRepository.findByTenantId(tenantId);
    }

    public User addUserForTenant(String tenantId, User user) {
        user.setTenantId(tenantId);
        return userRepository.save(user);
    }

    public User updateUserForTenant(String tenantId, User user) {
        if (!user.getTenantId().equals(tenantId)) {
            throw new IllegalArgumentException("User does not belong to this tenant");
        }
        return userRepository.save(user);
    }

    public void deleteUserForTenant(String tenantId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getTenantId().equals(tenantId)) {
            throw new IllegalArgumentException("User does not belong to this tenant");
        }

        userRepository.delete(user);
    }
}
