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
public class Flight {
    private String numberFlight;
    private String origin;
    private String destiny;
    private String seatType;
    private double priceForPerson;
    private LocalDate departuraDate;
    private LocalDate retunrDate;
}
