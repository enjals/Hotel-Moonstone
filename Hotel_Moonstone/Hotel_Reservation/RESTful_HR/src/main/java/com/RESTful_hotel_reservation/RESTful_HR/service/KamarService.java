package com.RESTful_hotel_reservation.RESTful_HR.service;

import com.RESTful_hotel_reservation.RESTful_HR.entity.Kamar;
import com.RESTful_hotel_reservation.RESTful_HR.entity.Kamar.TipeKamarEnum;
import com.RESTful_hotel_reservation.RESTful_HR.repository.KamarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class KamarService {

    @Autowired
    private KamarRepository roomRepository;

    // Folder untuk menyimpan file
    private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    // Nama domain diambil dari properti
    @Value("${app.domain}")
    private String domain;

    public Kamar addRoom(String namaKamar, Integer jumlahKasur, Integer jumlahOrang, String deskripsiKamar, String roomNumber, String roomType, double price, MultipartFile imgFile) throws Exception {
        // Nama file berdasarkan timestamp
        String fileName = System.currentTimeMillis() + "_" + imgFile.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR, fileName);

        // Membuat direktori jika belum ada
        Files.createDirectories(filePath.getParent());

        // Menyimpan file di lokasi
        Files.write(filePath, imgFile.getBytes());

        // Menyimpan URL gambar dengan nama domain
        String imgUrl = domain + "/uploads/" + fileName;

        // Simpan detail kamar di database
        Kamar room = new Kamar();
        room.setNomorKamar(roomNumber);
        room.setNamaKamar(namaKamar);
        room.setDeskripsi(deskripsiKamar);
        room.setJumlahOrang(jumlahOrang);
        room.setJumlahKasur(jumlahKasur);
        room.setTipeKamar(TipeKamarEnum.fromString(roomType));
        room.setHarga(price);
        room.setFotoKamar(imgUrl); // Menyimpan URL path gambar dengan domain
        room.setKetersediaan(true);

        return roomRepository.save(room);
    }

     // Mengedit kamar yang sudah ada
     public Kamar editRoom(Long roomId, String namaKamar, Integer jumlahKasur, Integer jumlahOrang, String deskripsiKamar, String roomNumber, String roomType, double price, MultipartFile imgFile) throws Exception {
        Kamar room = roomRepository.findById(roomId)
                .orElseThrow(() -> new Exception("Kamar tidak ditemukan dengan ID: " + roomId));

        // Memeriksa jika gambar baru ada
        if (imgFile != null && !imgFile.isEmpty()) {
            // Menghapus gambar lama jika ada
            String oldFileName = room.getFotoKamar().substring(room.getFotoKamar().lastIndexOf("/") + 1);
            Path oldFilePath = Paths.get(UPLOAD_DIR, oldFileName);
            Files.deleteIfExists(oldFilePath);

            // Menyimpan file baru
            String fileName = System.currentTimeMillis() + "_" + imgFile.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR, fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, imgFile.getBytes());

            // Mengupdate URL gambar baru
            String imgUrl = domain + "/uploads/" + fileName;
            room.setFotoKamar(imgUrl);
        }

        // Update detail kamar
        room.setNamaKamar(namaKamar);
        room.setJumlahKasur(jumlahKasur);
        room.setJumlahOrang(jumlahOrang);
        room.setDeskripsi(deskripsiKamar);
        room.setNomorKamar(roomNumber);
        room.setTipeKamar(TipeKamarEnum.fromString(roomType));
        room.setHarga(price);

        return roomRepository.save(room);
    }

    // Menghapus kamar berdasarkan ID
    public void deleteRoom(Long roomId) throws Exception {
        Kamar room = roomRepository.findById(roomId)
                .orElseThrow(() -> new Exception("Kamar tidak ditemukan dengan ID: " + roomId));

        // Menghapus file gambar terkait
        String fileName = room.getFotoKamar().substring(room.getFotoKamar().lastIndexOf("/") + 1);
        Path filePath = Paths.get(UPLOAD_DIR, fileName);
        Files.deleteIfExists(filePath);

        // Menghapus kamar dari database
        roomRepository.delete(room);
    }
}