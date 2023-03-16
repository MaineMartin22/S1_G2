package com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight;


import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PaymentMethodDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PeopleDto;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

<<<<<<< HEAD

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PositiveOrZero;
=======
>>>>>>> 56bddb8ef08e9ef1d389fe96edf8f261426ea25e
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FlightReservationDTO {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateTo;
    @NotNull(message="El origen elegido no existe")
    private String origin;

    private String flightNumber;

    @NotNull(message="El destino elegido no existe")
    private String destination;
    private Integer seats;
    private String seatType;
    private PeopleDto people;
    private PaymentMethodDto paymentMethod;
}