package com.sprint1.AgenciaDeTurismo.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;


import javax.validation.constraints.NotBlank;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HotelDTO {
    private Integer id;
    private String hotelCode;
    private String name;
    @NotBlank(message = "La ciudad no puede estar vacía")
    private String city;
    private String  typeRoom;
    private double  priceForNight;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate availabilityFrom;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate  availabilityUntil;
    private boolean reserved;


}