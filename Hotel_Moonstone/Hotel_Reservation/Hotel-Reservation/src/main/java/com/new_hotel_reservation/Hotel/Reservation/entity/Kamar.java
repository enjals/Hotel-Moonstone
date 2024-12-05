package com.new_hotel_reservation.Hotel.Reservation.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "room")
public class Kamar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String namaKamar;

    @Column(name = "img_url", nullable = false)
    private String fotoKamar;

    @Column(name = "room_number", nullable = false, unique = true)
    private String nomorKamar;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type", nullable = false)
    private TipeKamar tipeKamar;

    @Column(name = "description", nullable = false)
    private String deskripsi;

    @Column(name = "person_count", nullable = false)
    private int jumlahOrang;

    @Column(name = "bed_count", nullable = false)
    private int jumlahKasur;

    @Column(name = "price", nullable = false)
    private Double harga;

    @Column(name = "availability", nullable = false)
    private Boolean ketersediaan;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime dibuatPada;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime diperbaruiPada;

    // Getters dan Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFotoKamar() {
        return fotoKamar;
    }

    public void setFotoKamar(String fotoKamar) {
        this.fotoKamar = fotoKamar;
    }

    public String getNamaKamar() {
        return namaKamar;
    }

    public void setNamaKamar(String namaKamar) {
        this.namaKamar = namaKamar;
    }

    public String getNomorKamar() {
        return nomorKamar;
    }

    public void setNomorKamar(String nomorKamar) {
        this.nomorKamar = nomorKamar;
    }

    public TipeKamar getTipeKamar() {
        return tipeKamar;
    }

    public void setTipeKamar(TipeKamar tipeKamar) {
        this.tipeKamar = tipeKamar;
    }

    public int getJumlahOrang() {
        return jumlahOrang;
    }

    public void setJumlahOrang(int jumlahOrang) {
        this.jumlahOrang = jumlahOrang;
    }

    public int getJumlahKasur() {
        return jumlahKasur;
    }

    public void setJumlahKasur(int jumlahKasur) {
        this.jumlahKasur = jumlahKasur;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    public Boolean getKetersediaan() {
        return ketersediaan;
    }

    public void setKetersediaan(Boolean ketersediaan) {
        this.ketersediaan = ketersediaan;
    }

    public LocalDateTime getDibuatPada() {
        return dibuatPada;
    }

    public void setDibuatPada(LocalDateTime dibuatPada) {
        this.dibuatPada = dibuatPada;
    }

    public LocalDateTime getDiperbaruiPada() {
        return diperbaruiPada;
    }

    public void setDiperbaruiPada(LocalDateTime diperbaruiPada) {
        this.diperbaruiPada = diperbaruiPada;
    }
}