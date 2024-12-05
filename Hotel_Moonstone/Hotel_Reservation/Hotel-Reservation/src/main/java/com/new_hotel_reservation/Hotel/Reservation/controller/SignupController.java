package com.new_hotel_reservation.Hotel.Reservation.controller;

import com.new_hotel_reservation.Hotel.Reservation.entity.Login;
import com.new_hotel_reservation.Hotel.Reservation.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api")
public class SignupController {

    @Autowired
    private LoginService loginService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Sign Up - Only for Customers
    @PostMapping("/signup")
    public RedirectView signUp(@RequestBody Login login) {
        // Validasi jika ada data yang kosong
        if (login.getNik() == null || login.getFname() == null || login.getLname() == null || login.getEmail() == null || login.getPassword() == null) {
            return new RedirectView("/signup-page?error=missingFields");
        }

        // Cek apakah email sudah terdaftar
        boolean isEmailExist = loginService.isEmailExist(login.getEmail());

        if (isEmailExist) {
            return new RedirectView("/signup-page?error=emailExists");
        }

        // Enkripsi password sebelum menyimpan
        String encodedPassword = passwordEncoder.encode(login.getPassword());
        login.setPassword(encodedPassword);

        // Simpan data login ke database
        loginService.signUp(login);

        return new RedirectView("/login-page?signup=success");
    }
}