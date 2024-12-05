package com.new_hotel_reservation.Hotel.Reservation.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.new_hotel_reservation.Hotel.Reservation.entity.Kamar;
import com.new_hotel_reservation.Hotel.Reservation.entity.Pembayaran;
import com.new_hotel_reservation.Hotel.Reservation.entity.Pemesanan;
import com.new_hotel_reservation.Hotel.Reservation.service.KamarService;
import com.new_hotel_reservation.Hotel.Reservation.service.PembayaranService;
import com.new_hotel_reservation.Hotel.Reservation.service.PemesananService;



@Controller
@RequestMapping("/client")
public class ClientController {
    
        @Autowired
    private KamarService kamarService;

    @Autowired
    private PemesananService pemesananService;

    @Autowired
    private PembayaranService paymentService;

@GetMapping("/pesanan/{id}")
  public String getRoomDetails(@PathVariable("id") Long id, Model model) {
        Optional<Kamar> kamar = kamarService.getRoomById(id);  // Using instance method
        if (kamar.isPresent()) {
            model.addAttribute("kamar", kamar.get());  // Add room details to model
            return "/client/pesanan-page";  // Return the template for room details
        } else {
            model.addAttribute("error", "Kamar tidak ditemukan");
            return "/client/pesanan-page";  // Error page if room not found
        }
}

@GetMapping("/pembayaran/{id}")
public String getBookingDetails(
        @PathVariable("id") Long bookingId,
        @RequestParam("roomId") Long roomId,
        Model model) {
    
    // Ambil data pemesanan berdasarkan bookingId
    Optional<Pemesanan> pesanan = pemesananService.getPemesananById(bookingId);
    Optional<Kamar> kamar = kamarService.getRoomById(roomId);
    
    if (pesanan.isPresent()) {
        model.addAttribute("pesanan", pesanan.get()); // Tambahkan detail pemesanan ke model
        model.addAttribute("kamar", kamar.get()); // Tambahkan detail pemesanan ke model

        // Misalnya, jika Anda perlu memeriksa ID kamar:
        if (!pesanan.get().getRoom().getId().equals(roomId)) {
            model.addAttribute("error", "ID Kamar tidak sesuai dengan pemesanan");
            return "/client/error-page"; // Halaman error jika roomId tidak sesuai
        }
        
        return "/client/pembayaran-page"; // Kembali ke halaman pembayaran
    } else {
        model.addAttribute("error", "Pemesanan tidak ditemukan");
        return "/client/error-page"; // Halaman error jika bookingId tidak ditemukan
    }
}

@GetMapping("/receipt/{id}")
public String getReceiptDetails(
        @PathVariable("id") Long paymentId, // Menggunakan id pembayaran, bukan bookingId
        Model model) {
    
    // Ambil data pembayaran berdasarkan paymentId
    Optional<Pembayaran> payment = paymentService.getPembayaranById(paymentId);
    
    if (payment.isPresent()) {
        // Ambil data pemesanan berdasarkan bookingId yang ada di Payment
        Long bookingId = payment.get().getPemesanan().getId(); // Dapatkan bookingId dari Payment
        Optional<Pemesanan> pesanan = pemesananService.getPemesananById(bookingId);
        
        if (pesanan.isPresent()) {
            model.addAttribute("pesanan", pesanan.get()); // Tambahkan detail pemesanan ke model
            
            // Ambil data kamar yang terkait dengan pemesanan
            Optional<Kamar> kamar = kamarService.getRoomById(pesanan.get().getRoom().getId());
            
            if (kamar.isPresent()) {
                model.addAttribute("kamar", kamar.get()); // Tambahkan detail kamar ke model
            } else {
                model.addAttribute("error", "Room not found for the booking");
                return "/client/error-page"; // Jika kamar tidak ditemukan
            }

            model.addAttribute("payment", payment.get()); // Tambahkan detail pembayaran ke model
            return "/client/kwitansi-page"; // Kembali ke halaman receipt
        } else {
            model.addAttribute("error", "Booking not found for the given Payment ID");
            return "/client/error-page"; // Jika pemesanan tidak ditemukan
        }
    } else {
        model.addAttribute("error", "Payment not found for the given ID");
        return "/client/error-page"; // Jika pembayaran tidak ditemukan
    }
}

@GetMapping("/error-page")
public String tambahKamar(Model model) {
    return "/client/error-page"; // View untuk menampilkan daftar kamar
}
}
