package com.new_hotel_reservation.Hotel.Reservation.config;

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
            .authorizeRequests()
                .requestMatchers("/css/**", "/img/**", "/js/**", "/**", "/admin/**", "/client/**").permitAll()// Membebaskan akses ke halaman yang relevan
                .anyRequest().authenticated()  // Halaman lain memerlukan login
            .and()
            .formLogin()
                .loginPage("/login-page")
                .permitAll()
            .and()
            .logout()
                .permitAll();
        return http.build();
    }
}