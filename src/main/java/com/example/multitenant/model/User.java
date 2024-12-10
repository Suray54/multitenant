package com.example.multitenant.model;

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

    // Getters and Setters
}
