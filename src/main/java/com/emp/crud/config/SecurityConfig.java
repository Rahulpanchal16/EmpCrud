package com.emp.crud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for Postman testing
                .authorizeRequests()
                .requestMatchers("/api/**").permitAll() // Allow access to specific endpoints
                .anyRequest().authenticated() // All other requests require authentication
                .and()
                .httpBasic(Customizer.withDefaults()); // Enables basic authentication for testing

        return http.build();
    }
}
