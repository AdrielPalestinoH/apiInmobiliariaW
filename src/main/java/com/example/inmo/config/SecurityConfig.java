package com.example.inmo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilitamos CSRF para pruebas
            .authorizeHttpRequests(auth -> auth
                // Permitimos acceso sin login a Swagger y a los endpoints públicos
                .requestMatchers(
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/api/**"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(login -> login.disable()) // quitamos login form
            .httpBasic(basic -> basic.disable()); // quitamos Basic Auth

        return http.build();
    }
}
