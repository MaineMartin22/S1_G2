package com.sprint1.AgenciaDeTurismo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    private String numberFlight;
    private String origin;
    private String destiny;
    private String seatType;
    private String priceForPerson;
    private LocalDate departuraDate;
    private LocalDate retunrDate;
}
