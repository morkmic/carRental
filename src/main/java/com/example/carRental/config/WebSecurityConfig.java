package com.example.carRental.config;

import com.example.carRental.model.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/cars","cars/sortCars", "/registration").permitAll()
                .requestMatchers("/admin/**").hasRole(UserRole.ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        return http.build();
    }


}
