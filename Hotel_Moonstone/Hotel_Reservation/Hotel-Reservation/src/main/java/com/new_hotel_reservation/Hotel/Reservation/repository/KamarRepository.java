package com.new_hotel_reservation.Hotel.Reservation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.new_hotel_reservation.Hotel.Reservation.entity.Kamar;

public interface KamarRepository extends JpaRepository<Kamar, Long> {
    List<Kamar> findByKetersediaanTrue();
    List<Kamar> findAll();
}
