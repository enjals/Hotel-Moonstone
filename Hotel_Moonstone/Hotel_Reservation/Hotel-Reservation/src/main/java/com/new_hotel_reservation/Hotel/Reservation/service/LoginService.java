package com.new_hotel_reservation.Hotel.Reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.new_hotel_reservation.Hotel.Reservation.entity.Login;
import com.new_hotel_reservation.Hotel.Reservation.repository.LoginRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Login signUp(Login login) {
        // Cek apakah email sudah ada
        Optional<Login> existingUser  = loginRepository.findByEmail(login.getEmail());
        if (existingUser .isPresent()) {
            throw new IllegalStateException("Email already exists");
        }
        // Enkripsi password sebelum menyimpan
        String encryptedPassword = passwordEncoder.encode(login.getPassword());
        login.setPassword(encryptedPassword);
        
        return loginRepository.save(login);
    }

    public Optional<Login> login(String email, String password) {
        Optional<Login> user = loginRepository.findByEmail(email);
        if (user.isPresent()) {
            Login existingUser  = user.get();
            if (passwordEncoder.matches(password, existingUser .getPassword())) {
                return Optional.of(existingUser );
            }
        }
        return Optional.empty();  // Invalid email or password
    }

    public boolean isEmailExist(String email) {
        return loginRepository.existsByEmail(email);
    }

    public List<Login> getCustomers() {
        return loginRepository.findByIdAdminIsNull();
    }
}
