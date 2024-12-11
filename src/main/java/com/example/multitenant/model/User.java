package com.example.multitenant.model;

import com.example.multitenant.config.TenantContext;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    private String email;

    @Column(name = "tenant_id")
    private String tenantId;


    // This is need if Any value is needed for one tenant and not for other

//    @Column(nullable = true) // Can be nullable in the database
//    private String email;
//
//    // Add logic for tenant-specific validation
//    @PrePersist
//    @PreUpdate
//    public void validateTenantSpecificFields() {
//        String tenantId = TenantContext.getTenantId();
//        if ("tenant1".equals(tenantId) && (email == null || email.isEmpty())) {
//            throw new IllegalArgumentException("Email is required for tenant1");
//        }
//    }

//    CREATE TABLE IF NOT EXISTS user (
//            id INT AUTO_INCREMENT PRIMARY KEY,
//            name VARCHAR(255) NOT NULL,
//    email VARCHAR(255) NULL,
//    tenant_id VARCHAR(255) NOT NULL
//);
}
