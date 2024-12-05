package com.RESTful_hotel_reservation.RESTful_HR.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RESTful_hotel_reservation.RESTful_HR.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}