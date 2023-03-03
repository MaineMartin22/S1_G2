package com.sprint1.AgenciaDeTurismo.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Hotel {

    private String hotelCode;
    private String name;
    private String city;
    private String  typeRoom;
    private String  priceForNight;
    private LocalDate availabilityFrom;
    private LocalDate  availabilityUntil;
    private boolean reserved;


}
