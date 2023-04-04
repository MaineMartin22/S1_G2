package com.sprint1.AgenciaDeTurismo.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "Paquetes")
public class TouristicPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "descripcion", length = 250)
    private String description;

    @Column(name = "precio")
    private Integer price;

    @Column(name = "fecha_salida")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFrom;

    @Column(name = "fecha_vuelta")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateTo;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Hotel> hotels;

    @ManyToMany( cascade = CascadeType.ALL)
    private List<Flight> flights;
}
