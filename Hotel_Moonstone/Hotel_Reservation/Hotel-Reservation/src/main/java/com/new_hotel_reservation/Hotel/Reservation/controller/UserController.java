package com.new_hotel_reservation.Hotel.Reservation.controller;

import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/isLoggedIn")
    public Map<String, Boolean> isLoggedIn() {
        // Memeriksa apakah ada pengguna yang sedang login
        boolean loggedIn = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
        return Map.of("loggedIn", loggedIn);
    }
}