package com.RESTful_hotel_reservation.RESTful_HR.service;

import com.RESTful_hotel_reservation.RESTful_HR.dto.LoginRequest;
import com.RESTful_hotel_reservation.RESTful_HR.entity.Customer;
import com.RESTful_hotel_reservation.RESTful_HR.entity.Login;
import com.RESTful_hotel_reservation.RESTful_HR.repository.CustomerRepository;
import com.RESTful_hotel_reservation.RESTful_HR.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;

        @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void signup(Customer customer) {
        if (loginRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists.");
        }
        if (customer.getPassword() == null || customer.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        String encryptedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encryptedPassword);
    
        // Set default role jika belum diatur
        if (customer.getRole() == null) {
            customer.setRole(Login.Role.CUSTOMER;) // Role.USER adalah nilai default
        }
    
        // Simpan Customer ke dalam tabel customers dengan id yang diatur otomatis
        customerRepository.save(customer);
    }

    public Login login(LoginRequest loginRequest) {
        Optional<Login> loginOpt = loginRepository.findByEmail(loginRequest.getEmail());

        if (loginOpt.isEmpty() || !passwordEncoder.matches(loginRequest.getPassword(), loginOpt.get().getPassword())) {
            throw new RuntimeException("Invalid email or password.");
        }

        return loginOpt.get();
    }
}
