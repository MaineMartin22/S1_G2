package com.sprint1.AgenciaDeTurismo.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Entity
@Table(name = "hoteles")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "codigo_hotel")
    private String hotelCode;
    @Column(name = "nombre")
    private String name;
    @Column(name = "ciudad")
    private String city;
    @Column(name = "tipo_habitacion")
    private String  typeRoom;
    @Column(name = "precio_noche")
    private Integer  priceForNight;
    @Column(name = "disponible_desde")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate availabilityFrom;
    @Column(name = "disponible_hasta")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate  availabilityUntil;
    @Column(name = "reservado")
    private boolean reserved;


}
