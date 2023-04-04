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
@Table(name = "package")
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "codigo_paquete")
    private String codePackage;
    @Column(name = "precio")
    private double price;
    @Column(name = "ciudad")
    private String city;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Flight flight;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Hotel hotel;

}
