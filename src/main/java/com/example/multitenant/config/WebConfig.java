package com.example.multitenant.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Register the TenantInterceptor for all requests
        registry.addInterceptor(new TenantInterceptor()).addPathPatterns("/**");
    }
}
