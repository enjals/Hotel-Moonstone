package com.new_hotel_reservation.Hotel.Reservation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.new_hotel_reservation.Hotel.Reservation.entity.Pembayaran;

@Repository
public interface PembayaranRepository extends JpaRepository<Pembayaran, Long> {
    Optional<Pembayaran> findByPemesananId(Long pemesanan);
}
