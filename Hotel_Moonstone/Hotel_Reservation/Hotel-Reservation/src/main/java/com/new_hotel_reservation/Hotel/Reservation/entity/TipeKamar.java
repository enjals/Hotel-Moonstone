package com.new_hotel_reservation.Hotel.Reservation.entity;

public enum TipeKamar {
    SINGLE, DOUBLE, SUITE;
    @Override
    public String toString() {
        return name(); // default name() is usually fine, returns the enum name as String
    }
}
