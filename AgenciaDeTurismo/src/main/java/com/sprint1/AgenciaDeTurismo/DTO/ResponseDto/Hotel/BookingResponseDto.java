package com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Hotel;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PeopleDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookingResponseDto {
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String destination;
    private String hotelCode;
    private Integer peopleAmount;
    private String roomType;
    private List<PeopleDto> people;
}
