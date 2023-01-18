package com.leasing.administrator.leasingadministratorbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
@Profile("dev")
public class WebConfig implements WebMvcConfigurer {

    private static final String allowedOrigin = "http://localhost:4200";
    private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String PUT = "PUT";
    private static final String DELETE = "DELETE";
    private static final String HEAD = "HEAD";
    private static final String PATCH = "PATCH";


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(allowedOrigin).allowedMethods(GET, POST, PUT, DELETE,
                        HEAD, PATCH).allowCredentials(true);
    }

}
