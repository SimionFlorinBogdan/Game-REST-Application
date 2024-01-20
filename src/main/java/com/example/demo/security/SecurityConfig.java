package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT user_name, pw, active FROM users WHERE user_name=?");

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT user_name, role FROM roles WHERE user_name=?");
        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/killer/stats", "/killer/stats/**", "/killer/best-performance/**",
                                "/victim/stats", "/victim/stats/**", "/victim/best-performance/**").hasRole("PLAYER")
                        .requestMatchers(HttpMethod.POST, "/killer/stats", "/victim/stats").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/killer/stats", "/victim/stats").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/killer/stats", "/killer/stats/**",
                                "/victim/stats", "/victim/stats/**").hasRole("ADMIN")
        );

        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}















