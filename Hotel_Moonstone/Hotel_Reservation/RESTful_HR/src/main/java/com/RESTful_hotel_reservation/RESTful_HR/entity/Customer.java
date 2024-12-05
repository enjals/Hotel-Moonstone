package com.RESTful_hotel_reservation.RESTful_HR.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "customers")
public class Customer extends Login {

    @Override
    public String getSpecificId() {
        return this.getRole() == Role.CUSTOMER ? String.valueOf(this.getId()) : null;
    }
}
