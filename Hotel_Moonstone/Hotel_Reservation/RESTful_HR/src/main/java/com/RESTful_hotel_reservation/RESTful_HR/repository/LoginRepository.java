package com.RESTful_hotel_reservation.RESTful_HR.repository;

import com.RESTful_hotel_reservation.RESTful_HR.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Long> {
    Optional<Login> findByEmail(String email);
    Optional<Login> findByUsername(String username);
}
