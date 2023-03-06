package com.sprint1.AgenciaDeTurismo.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
