package com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight;


import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PaymentMethodDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PeopleDto;
import com.sprint1.AgenciaDeTurismo.Exception.Validations.FlightDateValidation;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FlightDateValidation
public class FlightReservationDTO {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFrom;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateTo;

    @NotNull(message ="El origen no puede estar vacío")
    private String origin;
    private String flightNumber;

    @NotNull(message ="El destino no puede estar vacío")
    private String destination;
    private Integer seats;
    private String seatType;
    private PeopleDto people;
    private PaymentMethodDto paymentMethod;
}