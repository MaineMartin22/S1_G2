package com.sprint1.AgenciaDeTurismo.DTO;

import com.sprint1.AgenciaDeTurismo.Entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PackageDTO {
    private Integer id;
    private String codePackage;
    private double price;
    private String city;
    private Flight flight;
    private Hotel hotel;
}
