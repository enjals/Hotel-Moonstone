package com.RESTful_hotel_reservation.RESTful_HR.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RESTful_hotel_reservation.RESTful_HR.entity.Booking;
import com.RESTful_hotel_reservation.RESTful_HR.repository.BookingRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Long id, Booking bookingDetails) {
        return bookingRepository.findById(id).map(booking -> {
            booking.setCustomerId(bookingDetails.getCustomerId());
            booking.setRoomId(bookingDetails.getRoomId());
            booking.setCheckIn(bookingDetails.getCheckIn());
            booking.setCheckOut(bookingDetails.getCheckOut());
            booking.setStatus(bookingDetails.getStatus());
            booking.setPaymentStatus(bookingDetails.getPaymentStatus());
            booking.setTotalAmount(bookingDetails.getTotalAmount());
            return bookingRepository.save(booking);
        }).orElseThrow(() -> new RuntimeException("Booking not found with id " + id));
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}