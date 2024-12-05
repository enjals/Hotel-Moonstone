package com.RESTful_hotel_reservation.RESTful_HR.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignupRequest {

    @NotBlank(message = "NIK tidak boleh kosong.")
    @Size(min = 16, max = 16, message = "NIK harus terdiri dari 16 karakter.")
    private String nik;

    @NotBlank(message = "Nama depan tidak boleh kosong.")
    private String fname;

    @NotBlank(message = "Nama belakang tidak boleh kosong.")
    private String lname;

    @NotBlank(message = "Email tidak boleh kosong.")
    @Email(message = "Email harus dalam format yang valid.")
    private String email;

    @NotBlank(message = "Password tidak boleh kosong.")
    @Size(min = 8, message = "Password harus terdiri dari minimal 8 karakter.")
    private String password;

    // Getters and Setters

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SignupRequest{" +
                "nik='" + nik + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", password='[PROTECTED]'" +
                '}';
    }
}
