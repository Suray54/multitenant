package com.example.multitenant.repository;

import com.example.multitenant.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByTenantId(String tenantId);
}
