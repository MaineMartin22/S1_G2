package com.sprint1.AgenciaDeTurismo.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PaymentMethodDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PeopleDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "fecha_ingreso")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String dateFrom;
    @Column(name = "fecha_salida")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String dateTo;
    @Column(name = "destino")
    private String destination;
    @Column(name = "codigo_hotel")
    private String hotelCode;
    @Column(name = "cantidad_persona")
    private Integer peopleAmount;
    @Column(name = "tipo_habitacion")
    private String roomType;
    @ManyToMany
    private List<People> people;
    @OneToOne
    private PaymentMethod paymentMethod;

}
