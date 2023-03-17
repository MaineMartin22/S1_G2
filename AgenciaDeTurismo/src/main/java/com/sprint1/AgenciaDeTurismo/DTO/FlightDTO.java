package com.sprint1.AgenciaDeTurismo.DTO;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PeopleDto;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FlightDTO {
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String origin;

    private String destination;
    private String flightNumber;
    private Integer seats;
    private String seatType;
    private List<PeopleDto> people;

}
