package com.sprint1.AgenciaDeTurismo.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Entity
@Table(name = "booking_hotel")
public class BookingHotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre_usuario", unique = true)
    private String userName;
    @Column(name = "total_neto")
    private double totalNeto;
    @Column(name = "total_intereses")
    private double totalIntereses;
    @Column(name = "total_final")
    private double totalFinal;
    @OneToOne(cascade = {CascadeType.ALL})
    private BookingHotelDetails booking;
}

