package com.luis.curso.springboot.app.springbootcrud.security;

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

import com.luis.curso.springboot.app.springbootcrud.security.filter.JwtAuthenticationFilter;

@Configuration
public class SpringSecurityConfig {
    // para el encriptado de las contraseñas

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Permiter generar y obtener el AuthenticationManager de nuestra aplicacion de
    // springSecurity

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean // filtro donde valida los request, autorizar, validadr y denegar permisos
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // return null;
        return http.authorizeHttpRequests(
                (authz) -> authz
                        .requestMatchers(HttpMethod.GET, "/api/users").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/users/register").permitAll()
                        .anyRequest().authenticated())
                .csrf(config -> config.disable()).addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();

    }

}
