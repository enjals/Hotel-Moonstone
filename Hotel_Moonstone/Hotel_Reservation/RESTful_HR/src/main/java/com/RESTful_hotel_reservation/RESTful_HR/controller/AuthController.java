package com.RESTful_hotel_reservation.RESTful_HR.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.RESTful_hotel_reservation.RESTful_HR.dto.LoginRequest;
import com.RESTful_hotel_reservation.RESTful_HR.dto.SignupRequest;
import com.RESTful_hotel_reservation.RESTful_HR.entity.Customer;
import com.RESTful_hotel_reservation.RESTful_HR.entity.Login;
import com.RESTful_hotel_reservation.RESTful_HR.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService loginService;

    @PostMapping("/signup")
    public String addUser(@RequestBody SignupRequest request, Model model) {
        Customer customer = new Customer();
        customer.setNik(request.getNik());
        customer.setFname(request.getFname());
        customer.setLname(request.getLname());
        customer.setEmail(request.getEmail());
        customer.setPassword(request.getPassword());
        loginService.signup(customer);
        
        model.addAttribute("successMessage", "Pendaftaran berhasil! Silakan login.");
        return "redirect:/login"; 
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Login user = loginService.login(request);
            if (user != null) {
            // Jika login berhasil, kirimkan data pengguna ke frontend
             return ResponseEntity.ok(user);  // Bisa juga mengirim token JWT jika menggunakan JWT
            } else {
                    // Jika login gagal, kirimkan status error
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}