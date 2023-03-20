package com.sprint1.AgenciaDeTurismo.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HotelDTO {

    private String hotelCode;
    private String name;
    private String city;
    private String  typeRoom;
    private double  priceForNight;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate availabilityFrom;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate  availabilityUntil;
    private boolean reserved;


}