package com.new_hotel_reservation.Hotel.Reservation.controller;

import com.new_hotel_reservation.Hotel.Reservation.entity.Login;
import com.new_hotel_reservation.Hotel.Reservation.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Login - Shared by Admin and Customer
    @PostMapping("/login")
    public RedirectView login(@RequestParam String email, @RequestParam String password) {
        // Validasi input
        if (email == null || password == null) {
            return new RedirectView("/login-page?error=missingFields");
        }

        Optional<Login> user = loginService.login(email, password);

        if (user.isPresent()) {
            // Compare password
            if (passwordEncoder.matches(password, user.get().getPassword())) {
                Login loggedInUser  = user.get();
                // Redirect based on user role (Admin or Customer)
                RedirectView redirectView = new RedirectView();
                if (loggedInUser .getIdAdmin() != null) {
                    // Redirect to Admin dashboard
                    redirectView.setUrl("/admin/index");
                } else {
                    // Redirect to Customer homepage
                    redirectView.setUrl("/index");  // Halaman customer
                }
                return redirectView;  // Return RedirectView for actual redirect
            } else {
                return new RedirectView("/login-page?error=passwordMismatch");  // Redirect with error if password doesn't match
            }
        }

        return new RedirectView("/login-page?error=userNotFound");  // Redirect with error if user not found
    }
}