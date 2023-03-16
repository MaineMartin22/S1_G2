package com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PaymentMethodDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PeopleDto;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PositiveOrZero;
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