package com.new_hotel_reservation.Hotel.Reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.new_hotel_reservation.Hotel.Reservation.entity.Login;
import com.new_hotel_reservation.Hotel.Reservation.entity.TipeRole;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Login, Integer> {
    
    // Menemukan Login berdasarkan email
    Login findByEmail(String email);

    // Menemukan Login berdasarkan username
    Login findByUsername(String username);

    // Menemukan Login berdasarkan role 'CUSTOMER'
    List<Login> findByRole(TipeRole role);

    // Menemukan Login berdasarkan role 'CUSTOMER' dan email
    Login findByRoleAndEmail(TipeRole role, String email);

    // Menemukan Login berdasarkan role 'CUSTOMER' dan username
    Login findByRoleAndUsername(TipeRole role, String username);
}
