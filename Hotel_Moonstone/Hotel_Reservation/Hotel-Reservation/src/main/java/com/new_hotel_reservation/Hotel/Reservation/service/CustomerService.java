package com.new_hotel_reservation.Hotel.Reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.new_hotel_reservation.Hotel.Reservation.entity.Login;
import com.new_hotel_reservation.Hotel.Reservation.entity.TipeRole;
import com.new_hotel_reservation.Hotel.Reservation.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String encryptPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    // Simpan customer (login) dengan password terenkripsi
    public Login saveCustomer(Login login) {
        if (login.getPassword() != null) {
            String encodedPassword = passwordEncoder.encode(login.getPassword());
            login.setPassword(encodedPassword);
        }
        return customerRepository.save(login);
    }

    // Ambil semua customer dengan role 'CUSTOMER'
    public List<Login> getAllCustomers() {
        return customerRepository.findByRole(TipeRole.CUSTOMER);
    }

    // Daftarkan customer baru dengan role 'CUSTOMER'
    public Login registerCustomer(Login login) {
        if (login.getPassword() != null) {
            String encodedPassword = passwordEncoder.encode(login.getPassword());
            login.setPassword(encodedPassword);
        }
        login.setTipeRole(TipeRole.CUSTOMER);  // Set role sebagai CUSTOMER
        return customerRepository.save(login);
    }

    // Perbarui customer dengan ID yang diberikan
    public Login updateCustomer(int id, Login updatedLogin) {
        Optional<Login> existingLoginOptional = customerRepository.findById(id);

        if (existingLoginOptional.isPresent()) {
            Login existingLogin = existingLoginOptional.get();

            // Perbarui detail login jika ada
            if (updatedLogin.getUsername() != null) {
                existingLogin.setUsername(updatedLogin.getUsername());
            }
            if (updatedLogin.getEmail() != null) {
                existingLogin.setEmail(updatedLogin.getEmail());
            }
            if (updatedLogin.getPassword() != null) {
                // Enkripsi password dan perbarui jika disediakan
                String encodedPassword = passwordEncoder.encode(updatedLogin.getPassword());
                existingLogin.setPassword(encodedPassword);
            }

            // Simpan login yang sudah diperbarui
            return customerRepository.save(existingLogin);
        } else {
            // Customer tidak ditemukan, kembalikan null atau throw exception
            return null;
        }
    }

    // Ambil customer berdasarkan ID
    public Optional<Login> getCustomerById(int id) {
        return customerRepository.findById(id);
    }

    // Hapus customer berdasarkan ID
    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }
}
