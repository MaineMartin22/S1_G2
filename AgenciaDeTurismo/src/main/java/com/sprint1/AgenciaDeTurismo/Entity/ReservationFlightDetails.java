package com.sprint1.AgenciaDeTurismo.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PeopleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Entity
@Table(name = "reserva_vuelo_detalle")
public class ReservationFlightDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "fecha_salida")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFrom;
    @Column(name = "fecha_vuelta")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateTo;
    @Column(name = "origen")
    private String origin;
    @Column(name = "destino")
    private String destiny;
    @Column(name = "codigo_vuelo")
    private String flightNumber;
    @Column(name = "cantidad_asientos")
    private Integer seats;
    @Column(name = "tipo_asiento")
    private String seatType;
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<People> people;

}
