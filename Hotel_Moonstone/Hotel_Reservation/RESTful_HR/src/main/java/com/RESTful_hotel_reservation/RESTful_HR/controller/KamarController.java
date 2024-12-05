package com.RESTful_hotel_reservation.RESTful_HR.controller;

import com.RESTful_hotel_reservation.RESTful_HR.entity.Kamar;
import com.RESTful_hotel_reservation.RESTful_HR.service.KamarService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/kamar")
public class KamarController {

    @Autowired
    private KamarService kamarService;

    // Endpoint untuk menambahkan kamar
    @PostMapping("/add")
    public ResponseEntity<?> addRoom(
        @RequestParam("namaKamar") String namaKamar,
        @RequestParam("roomNumber") String roomNumber,
        @RequestParam("roomType") String roomType,
        @RequestParam("price") double price,
        @RequestParam("imgFile") MultipartFile imgFile,
        @RequestParam("jumlahKasur") Integer jumlahKasur,
        @RequestParam("jumlahOrang") Integer jumlahOrang,
        @RequestParam("deskripsiKamar") String deskripsiKamar) {
        
        try {
            // Memanggil service untuk menambahkan kamar
            Kamar room = kamarService.addRoom(namaKamar, jumlahKasur, jumlahOrang, deskripsiKamar, roomNumber, roomType, price, imgFile);
            return ResponseEntity.ok().body("Kamar berhasil ditambahkan dengan ID: " + room.getId());
        }  catch (IOException e) {
            return ResponseEntity.status(500).body("Error uploading image: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Terjadi kesalahan: " + e.getMessage());
        }
    }

      // Endpoint untuk mengedit kamar
      @PutMapping("/edit/{id}")
      public ResponseEntity<?> editRoom(
              @PathVariable Long id,
              @RequestParam String namaKamar,
              @RequestParam Integer jumlahKasur,
              @RequestParam Integer jumlahOrang,
              @RequestParam String deskripsiKamar,
              @RequestParam String roomNumber,
              @RequestParam String roomType,
              @RequestParam double price,
              @RequestParam(required = false) MultipartFile imgFile) {
                System.out.println("namaKamar: " + namaKamar);
                System.out.println("roomNumber: " + roomNumber);
          try {
              Kamar room = kamarService.editRoom(id, namaKamar, jumlahKasur, jumlahOrang, deskripsiKamar, roomNumber, roomType, price, imgFile);
              return ResponseEntity.ok().body("Kamar berhasil diperbarui dengan ID: " + room.getId());
          } catch (Exception e) {
              return ResponseEntity.status(500).body("Terjadi kesalahan: " + e.getMessage());
          }
      }
  
      // Endpoint untuk menghapus kamar
      @DeleteMapping("/delete/{id}")
      public ResponseEntity<?> deleteRoom(@PathVariable Long id) {
          try {
              kamarService.deleteRoom(id);
              return ResponseEntity.ok().body("Kamar berhasil dihapus");
          } catch (Exception e) {
              return ResponseEntity.status(500).body("Terjadi kesalahan: " + e.getMessage());
          }
      }
}
