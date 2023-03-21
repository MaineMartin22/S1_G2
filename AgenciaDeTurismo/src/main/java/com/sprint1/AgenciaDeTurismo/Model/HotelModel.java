package com.sprint1.AgenciaDeTurismo.Model;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class HotelModel {

    private String hotelCode;
    private String name;
    private String city;
    private String  typeRoom;
    private Integer  priceForNight;
    private LocalDate availabilityFrom;
    private LocalDate  availabilityUntil;
    private boolean reserved;


}
