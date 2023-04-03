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
@Table(name = "vuelos")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "codigo_vuelo")
    private String numberFlight;
    @Column(name = "origen")
    private String origin;
    @Column(name = "destino")
    private String destiny;
    @Column(name = "tipo_asiento")
    private String seatType;
    @Column(name = "precio_persona")
    private double priceForPerson;
    @Column(name = "fecha_salida")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFrom;
    @Column(name = "fecha_vuelta")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateTo;
}
