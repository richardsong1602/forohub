package com.example.forohub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
           .authorizeRequests(authorize -> authorize
               .antMatchers(HttpMethod.POST, "/api/topicos").authenticated() // Requiere autenticación para POST /api/topicos
               .anyRequest().permitAll() // Permite todas las demás peticiones sin autenticación
            )
           .httpBasic(); // Usa autenticación HTTP Basic
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Agregamos varios usuarios con diferentes roles
        UserDetails userAdmin = User.builder()
           .username("admin")
           .password(passwordEncoder().encode("adminpassword")) // Codifica la contraseña usando BCryptPasswordEncoder
           .roles("ADMIN")
           .build();

        UserDetails userModerator = User.builder()
           .username("moderator")
           .password(passwordEncoder().encode("moderatorpassword")) // Codifica la contraseña usando BCryptPasswordEncoder
           .roles("MODERATOR")
           .build();

        UserDetails userUser = User.builder()
           .username("user")
           .password(passwordEncoder().encode("userpassword")) // Codifica la contraseña usando BCryptPasswordEncoder
           .roles("USER")
           .build();

        return new InMemoryUserDetailsManager(userAdmin, userModerator, userUser); // Gestiona usuarios en memoria
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Utiliza BCrypt para codificar contraseñas
    }
}