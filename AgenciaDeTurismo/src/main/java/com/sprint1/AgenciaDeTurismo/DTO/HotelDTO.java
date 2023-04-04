package com.sprint1.AgenciaDeTurismo.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;


import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HotelDTO {
    private Integer id;
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
    private Integer totalRooms;

}