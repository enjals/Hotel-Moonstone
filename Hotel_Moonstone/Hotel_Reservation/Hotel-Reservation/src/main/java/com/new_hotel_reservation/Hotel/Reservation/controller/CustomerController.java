package com.new_hotel_reservation.Hotel.Reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.new_hotel_reservation.Hotel.Reservation.entity.Login;
import com.new_hotel_reservation.Hotel.Reservation.service.CustomerService;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/admin/customer-detail/{id}")
    public String getCustomer(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.getCustomerById(id).orElse(null));
        return "admin/customers"; // Ganti dengan view Anda
    }

    @PostMapping("/admin/update-customer/{id}")
    public String updateCustomer(@PathVariable int id, @ModelAttribute Login customer) {
        customer.setId(id); // Pastikan ID tetap
        customerService.updateCustomer(id,customer);
        return "redirect:admin/customers";
    }

    @GetMapping("/admin/delete-customer/{id}")
    public String deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
        return "redirect:admin/customers";
    }
}
