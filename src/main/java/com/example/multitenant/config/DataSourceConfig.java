package com.example.multitenant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    private final Environment environment;

    public DataSourceConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public MultiTenantDataSource dataSource() {
        MultiTenantDataSource dataSource = new MultiTenantDataSource();

        // Dynamically create data sources from application.properties
        Map<Object, Object> targetDataSources = new HashMap<>();

        // Tenant configuration
        String[] tenantIds = {"tenant1", "tenant2"};

        for (String tenantId : tenantIds) {
            String url = environment.getProperty(tenantId + ".datasource.url");
            String username = environment.getProperty(tenantId + ".datasource.username");
            String password = environment.getProperty(tenantId + ".datasource.password");

            if (url != null && username != null && password != null) {
                targetDataSources.put(tenantId, createDataSource(url, username, password));
            }
        }

        // Set the target data sources and default data source
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(targetDataSources.get("tenant1"));

        return dataSource;
    }

    private DataSource createDataSource(String url, String username, String password) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
