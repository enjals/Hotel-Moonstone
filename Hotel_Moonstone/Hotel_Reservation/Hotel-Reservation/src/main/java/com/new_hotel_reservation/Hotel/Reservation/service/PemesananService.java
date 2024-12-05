package com.new_hotel_reservation.Hotel.Reservation.service;

import com.new_hotel_reservation.Hotel.Reservation.entity.Pemesanan;
import com.new_hotel_reservation.Hotel.Reservation.entity.StatusPemesanan;
import com.new_hotel_reservation.Hotel.Reservation.repository.PemesananRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PemesananService {

    @Autowired
    private PemesananRepository pemesananRepository;

    public List<Pemesanan> getAllPemesanan() {
        return pemesananRepository.findAll();
    }

    public Optional<Pemesanan> getPemesananById(Long id) {
        return pemesananRepository.findById(id);
    }

    public Pemesanan createPemesanan(Pemesanan pemesanan) {
        return pemesananRepository.save(pemesanan);
    }

    public Pemesanan updatePemesanan(Long id, Pemesanan pemesanan) {
        if (pemesananRepository.existsById(id)) {
            pemesanan.setId(id);
            return pemesananRepository.save(pemesanan);
        }
        return null;
    }

    public boolean deletePemesanan(Long id) {
        if (pemesananRepository.existsById(id)) {
            pemesananRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Pemesanan> processPayment(Long id) {
        Optional<Pemesanan> optionalPemesanan = pemesananRepository.findById(id);
        
        if (optionalPemesanan.isPresent()) {
            Pemesanan pemesanan = optionalPemesanan.get();
            
            // Periksa jika status belum PAID
            if (!pemesanan.getStatusPemesanan().equals(StatusPemesanan.PAID)) {
                pemesanan.setStatusPemesanan(StatusPemesanan.PAID);
                pemesananRepository.save(pemesanan);
                return Optional.of(pemesanan);
            }
        }
        
        return Optional.empty();  // Kembalikan empty jika pemesanan tidak ditemukan atau sudah PAID
    }
}
