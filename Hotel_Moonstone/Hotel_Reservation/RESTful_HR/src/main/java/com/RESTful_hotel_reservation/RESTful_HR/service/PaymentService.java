package com.RESTful_hotel_reservation.RESTful_HR.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RESTful_hotel_reservation.RESTful_HR.entity.Booking;
import com.RESTful_hotel_reservation.RESTful_HR.entity.Booking.PaymentStatus;
import com.RESTful_hotel_reservation.RESTful_HR.entity.Booking.Status;
import com.RESTful_hotel_reservation.RESTful_HR.entity.Payment;
import com.RESTful_hotel_reservation.RESTful_HR.entity.Payment.PaymentMethod;
import com.RESTful_hotel_reservation.RESTful_HR.entity.Payment.PaymentStatuses;
import com.RESTful_hotel_reservation.RESTful_HR.repository.BookingRepository;
import com.RESTful_hotel_reservation.RESTful_HR.repository.PaymentRepository;

import jakarta.transaction.Transactional;

@Service
public class PaymentService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    public Payment processPayment(Long bookingId, Double amount, PaymentMethod method,String cardNumber) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (booking.getStatus() == Status.CANCELLED) {
            throw new RuntimeException("Cannot process payment for cancelled booking");
        }

        // Create payment record
        Payment payment = new Payment();
        payment.setBookingId(bookingId);
        payment.setAmount(amount);
        payment.setPaymentMethod(method);
        payment.setCardNumber(cardNumber);
        payment.setPaymentDate(new Timestamp(System.currentTimeMillis()));
        payment.setStatus(PaymentStatuses.PENDING);

        Payment savedPayment = paymentRepository.save(payment);

        // Update booking status
        booking.setStatus(Status.PAID);
        booking.setPaymentStatus(PaymentStatus.PAID);
        bookingRepository.save(booking);

        // Mark payment as completed
        savedPayment.setStatus(PaymentStatuses.COMPLETED);
        return paymentRepository.save(savedPayment);
    }

    @Transactional
    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(Status.CANCELLED);
        bookingRepository.save(booking);
    }

    @Transactional
    public void confirmBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(Status.CONFIRMED);
        bookingRepository.save(booking);
    }
}
