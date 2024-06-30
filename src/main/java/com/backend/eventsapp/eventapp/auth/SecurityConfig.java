package com.backend.eventsapp.eventapp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.backend.eventsapp.eventapp.auth.filters.JwtAuthenticationFilter;
import com.backend.eventsapp.eventapp.auth.filters.JwtValidationFilter;

@Configuration
public class SecurityConfig {
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .sessionManagement(
                        sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        authorizeRequests -> authorizeRequests
                                // EVENTS
                                .requestMatchers(HttpMethod.GET, "/events").permitAll()
                                .requestMatchers(HttpMethod.GET, "/events/{id}").permitAll()
                                .requestMatchers(HttpMethod.POST, "/events").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/events/**").hasAnyRole("ADMIN", "USER")

                                // CATEGORIES
                                .requestMatchers(HttpMethod.GET, "/categories").permitAll()
                                .requestMatchers(HttpMethod.GET, "/categories/{id}").permitAll()
                                .requestMatchers(HttpMethod.POST, "/categories").hasAnyRole("ADMIN")
                                .requestMatchers("/categories/**").hasAnyRole("ADMIN")

                                // POSTS
                                .requestMatchers(HttpMethod.GET, "/posts").permitAll()
                                .requestMatchers(HttpMethod.GET, "/posts/{id}").hasRole("USER")
                                .requestMatchers(HttpMethod.GET, "/posts/event/{id}").hasAnyRole("ADMIN", "USER")
                                .requestMatchers(HttpMethod.POST, "/posts").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/posts/**").hasAnyRole("ADMIN", "USER")

                                // ROLES
                                .requestMatchers("/roles").hasRole("ADMIN")
                                .requestMatchers("/roles/**").hasRole("ADMIN")

                                // USERS
                                .requestMatchers(HttpMethod.GET, "/users/{id}").hasRole("USER")
                                .requestMatchers(HttpMethod.POST, "/users").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/users/{id}").hasRole("USER")
                                .requestMatchers("/users/**").hasAnyRole("ADMIN", "USER")

                                .anyRequest().authenticated())
                .addFilter(new JwtAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationConfiguration.getAuthenticationManager()));

        return http.build();
    }
}
