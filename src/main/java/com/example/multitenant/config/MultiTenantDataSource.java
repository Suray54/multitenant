package com.example.multitenant.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultiTenantDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        String tenantId = TenantContext.getTenantId();

        return tenantId;
    }
}
