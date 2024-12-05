package com.RESTful_hotel_reservation.RESTful_HR.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RESTful_hotel_reservation.RESTful_HR.entity.Payment;
import com.RESTful_hotel_reservation.RESTful_HR.entity.Payment.PaymentMethod;
import com.RESTful_hotel_reservation.RESTful_HR.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/{bookingId}/pay")
    public ResponseEntity<Payment> payBooking(
            @PathVariable Long bookingId,
            @RequestParam Double amount,
            @RequestParam PaymentMethod method,
            @RequestParam String cardNumber
            ) {
        Payment payment = paymentService.processPayment(bookingId, amount, method, cardNumber);
        return ResponseEntity.ok(payment);
    }

    @PostMapping("/{bookingId}/cancel")
    public ResponseEntity<String> cancelBooking(@PathVariable Long bookingId) {
        paymentService.cancelBooking(bookingId);
        return ResponseEntity.ok("Booking cancelled successfully");
    }

    @PostMapping("/{bookingId}/confirm")
    public ResponseEntity<String> confirmBooking(@PathVariable Long bookingId) {
        paymentService.confirmBooking(bookingId);
        return ResponseEntity.ok("Booking confirmed successfully");
    }
}
