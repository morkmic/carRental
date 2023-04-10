package com.example.carRental.security;

import com.example.carRental.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    CustomUserDetailsService userDetailsService;
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();

    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/cars","cars/sortCars", "/register",
                        "/register/registerEmployee","cars/filtration/**").permitAll()
                .requestMatchers("/cars/carRent").hasAuthority(UserRole.USER.name())
                .requestMatchers("/cars/**").hasAuthority(UserRole.EMPLOYEE.name())
                .requestMatchers(HttpMethod.GET,"/users", "/users/{userid}").hasAuthority(UserRole.EMPLOYEE.name())
                .requestMatchers("/users/**", "/cars/**", "/employee/**", "/register").hasRole(UserRole.ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        return http.build();
    }
    public void configure(AuthenticationManagerBuilder builder) throws Exception{
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


}
