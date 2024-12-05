package com.new_hotel_reservation.Hotel.Reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.new_hotel_reservation.Hotel.Reservation.entity.Pembayaran;
import com.new_hotel_reservation.Hotel.Reservation.repository.PembayaranRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PembayaranService {

    @Autowired
    private PembayaranRepository pembayaranRepository;

    public List<Pembayaran> getAllPembayaran() {
        return pembayaranRepository.findAll();
    }

    public Optional<Pembayaran> getPembayaranById(Long id) {
        return pembayaranRepository.findById(id);
    }

    public Pembayaran savePembayaran(Pembayaran pembayaran) {
        return pembayaranRepository.save(pembayaran);
    }

    public void deletePembayaranById(Long id) {
        pembayaranRepository.deleteById(id);
    }

    public Optional<Pembayaran> getPaymentByBookingId(Long bookingId) {
        return pembayaranRepository.findByPemesananId(bookingId);
    }
}