package com.new_hotel_reservation.Hotel.Reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.new_hotel_reservation.Hotel.Reservation.entity.Kamar;
import com.new_hotel_reservation.Hotel.Reservation.entity.Login;
import com.new_hotel_reservation.Hotel.Reservation.entity.Pemesanan;
import com.new_hotel_reservation.Hotel.Reservation.repository.KamarRepository;
import com.new_hotel_reservation.Hotel.Reservation.service.KamarService;
import com.new_hotel_reservation.Hotel.Reservation.service.LoginService;
import com.new_hotel_reservation.Hotel.Reservation.service.PemesananService;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private PemesananService bookingService;

    @Autowired
    private KamarRepository roomRepository;

    @Autowired
    private LoginService loginService;

    @Autowired
    private KamarService kamarService;

    // Menampilkan daftar pemesanan
@GetMapping("/pemesanan")
public String viewOrders(Model model) {
    List<Pemesanan> bookings = bookingService.getAllPemesanan();

    // Menambahkan customer first name untuk setiap pemesanan
    List<String> customerNames = bookings.stream()
                                         .map(pemesanan -> pemesanan.getCustomerFName())
                                         .collect(Collectors.toList());
    List<String> roomNumbers = bookings.stream()
                                         .map(pemesanan -> pemesanan.getRoom() != null ? pemesanan.getRoom().getNomorKamar() : "N/A")
                                         .collect(Collectors.toList());
    // Menambahkan data bookings dan customer names ke model
    model.addAttribute("bookings", bookings);
    model.addAttribute("customerNames", customerNames);
    model.addAttribute("roomNumbers", roomNumbers);

    return "/admin/pemesanan"; // View untuk menampilkan daftar pemesanan
}

    // Menampilkan detail pemesanan dan memproses pembayaran
    @GetMapping("/pemesanan/{id}")
    public String viewOrderDetail(@PathVariable Long id, Model model) {
        Optional<Pemesanan> booking = bookingService.getPemesananById(id);
        model.addAttribute("booking", booking);
        return "/admin/pemesanan-detail"; // View untuk menampilkan detail pemesanan
    }

    @PostMapping("/pemesanan/{id}/pay")
    public String processPayment(@PathVariable Long id) {
        bookingService.processPayment(id); // Mengubah status pembayaran menjadi PAID
        return "redirect:/admin/pemesanan";
    }

    // Menampilkan daftar kamar
    @GetMapping("/kamar")
    public String viewRooms(Model model) {
        List<Kamar> kamar = roomRepository.findAll();
        model.addAttribute("kamar", kamar);
        return "/admin/kamar"; // View untuk menampilkan daftar kamar
    }

    @GetMapping("/kamar-detail/{id}")
    public String getRoomDetails(@PathVariable("id") Long id, Model model) {
        Optional<Kamar> kamar = kamarService.getRoomById(id);  // Using instance method
        if (kamar.isPresent()) {
            model.addAttribute("kamar", kamar.get());  // Add room details to model
            return "/admin/kamar-detail";  // Return the template for room details
        } else {
            model.addAttribute("error", "Kamar tidak ditemukan");
            return "/admin/kamar-detail";  // Error page if room not found
        }
    }
    
    @GetMapping("/tambah-kamar")
    public String tambahKamar(Model model) {
        return "/admin/room-form"; // View untuk menampilkan daftar kamar
    }

    // Menampilkan daftar customer
    @GetMapping("/customers")
    public String viewCustomers(Model model) {
        List<Login> customers = loginService.getCustomers();
        model.addAttribute("customers", customers);
        return "/admin/customers"; // View untuk menampilkan daftar customer
    }

        @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // Menghapus session untuk logout
        request.getSession().invalidate();
        return "redirect:/admin/logout";  // Mengarahkan ke halaman login setelah logout
    }
}
