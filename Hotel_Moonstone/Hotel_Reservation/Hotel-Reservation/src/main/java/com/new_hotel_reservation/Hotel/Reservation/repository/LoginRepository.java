package com.new_hotel_reservation.Hotel.Reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.new_hotel_reservation.Hotel.Reservation.entity.Login;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {

    Optional<Login> findByEmail(String email);
    boolean existsByNik(String nik);
    boolean existsByEmail(String email);
    List<Login> findByIdAdminIsNull();
}
