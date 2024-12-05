package com.new_hotel_reservation.Hotel.Reservation.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "login")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nik",length = 20, nullable = false)
    private String nik; // Mengubah Integer menjadi String untuk menyimpan NIK yang lebih panjang

    @Column(name = "id_admin",nullable = true)
    private Integer idAdmin; // Nullable karena bisa null untuk non-admin

    @Column(name = "fname",length = 150, nullable = false)
    private String fname;

    @Column(name = "lname",length = 150, nullable = false)
    private String lname;

    @Column(name = "username", nullable = true, unique = true)
    private String username;

    @Column(name = "email",length = 100, nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private TipeRole role;

    @Column(name = "password",length = 255, nullable = false)
    private String password; // Panjang 255 lebih aman untuk menyimpan password yang terenkripsi

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public Integer getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
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

    public TipeRole getTipeRole() {
        return role;
    }

    public void setTipeRole(TipeRole role) {
        this.role = role;
    }

    public String getUsername() {
            return username;
        
    }

    public void setUsername(String username) {
        this.username = username;
    }
}