package com.new_hotel_reservation.Hotel.Reservation.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
public class Pembayaran {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Pemesanan pemesanan;

    @Column(name = "amount", nullable = false)
    private Double jumlah;

    @Column(name = "payment_date", nullable = false, updatable = false)
    private LocalDateTime tanggalPembayaran;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private MetodePembayaran metodePembayaran;

    @Column(name = "card_number", nullable = false, updatable = false)
    private String cardNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusPembayaran status;

    @PrePersist
    public void onPayment() {
        this.tanggalPembayaran = LocalDateTime.now();
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pemesanan getPemesanan() {
        return pemesanan;
    }

    public void setPemesanan(Pemesanan pemesanan) {
        this.pemesanan = pemesanan;
    }

    public Double getJumlah() {
        return jumlah;
    }

    public void setJumlah(Double jumlah) {
        this.jumlah = jumlah;
    }

    public LocalDateTime getTanggalPembayaran() {
        return tanggalPembayaran;
    }

    public MetodePembayaran getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(MetodePembayaran metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public StatusPembayaran getStatus() {
        return status;
    }

    public void setStatus(StatusPembayaran status) {
        this.status = status;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

}
