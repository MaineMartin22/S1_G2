package com.sprint1.AgenciaDeTurismo.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TouristicPackageDTO {
    private Integer id;

    @NotNull(message ="El nombre no puede estar vacío")
    private String name;

    @NotNull(message ="La descripcion no puede estar vacío")
    private String description;


    private Integer price;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateFrom;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateTo;

    private List<HotelDTO> hotels;

    private List<FlightDto> flights;
}
