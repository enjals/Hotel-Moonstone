package com.new_hotel_reservation.Hotel.Reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.new_hotel_reservation.Hotel.Reservation.entity.Kamar;
import com.new_hotel_reservation.Hotel.Reservation.repository.KamarRepository;

import java.util.List;
import java.util.Optional;

@Service
public class KamarService {

    @Autowired
    private KamarRepository kamarRepository; // Hapus static di sini
    
    // Mendapatkan semua kamar
    public List<Kamar> getAllRooms() {
        return kamarRepository.findAll();
    }
    
    // Menambahkan kamar baru
    public void addRoom(Kamar kamar) {
        kamarRepository.save(kamar);
    }
    
    // Menghapus kamar
    public void deleteRoom(Long id) {
        kamarRepository.deleteById(id);
    }
    
    public Optional<Kamar> getRoomById(Long id) {
        return kamarRepository.findById(id);
    }
}
