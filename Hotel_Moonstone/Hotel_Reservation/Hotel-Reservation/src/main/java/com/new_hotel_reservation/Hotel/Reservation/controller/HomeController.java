package com.new_hotel_reservation.Hotel.Reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.new_hotel_reservation.Hotel.Reservation.entity.Kamar;
import com.new_hotel_reservation.Hotel.Reservation.repository.KamarRepository;

import org.springframework.ui.Model;

@Controller
public class HomeController {

    @Autowired
    private KamarRepository roomRepository;

    // Menangani permintaan GET untuk root URL ("/") dan mengarahkan ke index.html
    @GetMapping("/")
    public String home() {
        return "index";  // Nama file HTML (index.html) di folder templates
    }

    @GetMapping("/room-page")
    public String getRoomPage(Model model) {
        List<Kamar> availableRooms = roomRepository.findByKetersediaanTrue();
        if (availableRooms.isEmpty()) {
            System.out.println("No available rooms found");
        }
        model.addAttribute("availableRooms", availableRooms);
        return "room-page";
    }

    @GetMapping("/facilities")
    public String facilitiesPage() {
        return "facilities";  // Halaman login Anda
    }

    @GetMapping("/contact-page")
    public String contactPage() {
        return "contact-page";  // Halaman login Anda
    }

    @GetMapping("/login-page")
    public String loginPage() {
        return "login-page";  // Halaman login Anda
    }

    @GetMapping("/signup-page")
    public String signupPage() {
        return "signup-page";  // Halaman login Anda
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin/index";  // Halaman login Anda
    }

}
