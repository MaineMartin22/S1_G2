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
@Table(name = "booking_hotel_detalle")
public class BookingHotelDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "fecha_ingreso")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFrom;
    @Column(name = "fecha_salida")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateTo;
    @Column(name = "destino")
    private String destination;
    @Column(name = "codigo_hotel")
    private String hotelCode;
    @Column(name = "cantidad_persona")
    private Integer peopleAmount;
    @Column(name = "tipo_habitacion")
    private String roomType;
    @ManyToMany(cascade = {CascadeType.ALL})
    private List<People> people;

}
