package com.example.multitenant.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultiTenantDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        String tenantId = TenantContext.getTenantId();

        if (tenantId == null || tenantId.isEmpty()) {
            return "common";  // Use the common database if tenantId is not provided
        }

        return tenantId;
    }
}
