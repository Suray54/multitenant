package com.example.multitenant.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.List;

@Component
public class HibernateSchemaInitializer {

    // List of tenant schemas
    private final List<String> tenantSchemas = List.of("tenant1", "tenant2");

    // Common schema for Country table
    private final String commonSchema = "common";

    @PostConstruct
    public void generateSchemas() {
        // Generate schemas for each tenant
        tenantSchemas.forEach(this::generateTenantSchema);

        // Generate schema for common database (Country table)
        generateCommonSchema();
    }

    private void generateTenantSchema(String schema) {
        // Hibernate configuration setup for tenant schema
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.setProperty("hibernate.default_schema", schema);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/" + schema);
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "Potato123!");

        // Add tenant-specific entity classes (e.g., User)
        configuration.addAnnotatedClass(com.example.multitenant.model.User.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            // Schema is generated automatically for the tenant
        } catch (Exception e) {
            System.err.println("Error creating schema for tenant: " + schema);
            e.printStackTrace();
        }
    }

    private void generateCommonSchema() {
        // Hibernate configuration setup for common schema (Country table)
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.setProperty("hibernate.default_schema", commonSchema);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/common");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "Potato123!");

        // Add common entity classes (e.g., Country)
        configuration.addAnnotatedClass(com.example.multitenant.model.Country.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            // Schema is generated automatically for the common database
        } catch (Exception e) {
            System.err.println("Error creating schema for common database");
            e.printStackTrace();
        }
    }
}
