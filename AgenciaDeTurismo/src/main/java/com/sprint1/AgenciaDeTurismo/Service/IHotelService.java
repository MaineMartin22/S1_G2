package com.sprint1.AgenciaDeTurismo.Service;

import com.sprint1.AgenciaDeTurismo.DTO.DestinoMasSolicitado;
import com.sprint1.AgenciaDeTurismo.DTO.GananciasDTO;
import com.sprint1.AgenciaDeTurismo.DTO.HotelDTO;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel.BookingRequestDto;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Hotel.BookingResponseDTO;
import com.sprint1.AgenciaDeTurismo.Service.Generics.ICrudService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IHotelService extends ICrudService<HotelDTO, Integer> {
    List<BookingResponseDTO> getAllEntitiesResponse();
    BookingResponseDTO reservationHotel(@RequestBody BookingRequestDto bookingRequestDto);

    BookingResponseDTO updateReservaEntity(@RequestBody BookingResponseDTO bookingResponseDTO, Integer id);
    GananciasDTO totalEarnings();

    DestinoMasSolicitado getDestinoMasSolicitado();
}
