package com.sprint1.AgenciaDeTurismo.Controller;

import com.sprint1.AgenciaDeTurismo.DTO.ErrorDTO;
import com.sprint1.AgenciaDeTurismo.DTO.HotelDTO;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel.BookingRequestDto;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Hotel.BookingResponse;

import com.sprint1.AgenciaDeTurismo.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@RestController
@Validated
public class HotelController {

    @Autowired
    HotelService hotelService;
    //Alta de un nuevo hotel.
    @PostMapping("/api/v1/hotels/new")
    public HotelDTO saveEntity(@RequestBody HotelDTO hotelDTO) {
        return hotelService.saveEntity(hotelDTO);
    }

    // Busqueda de un hotel por su codigo.
    @GetMapping("/api/v1/hotels/findOneWhit")
    public HotelDTO findHotelByCode(@RequestParam String code) {
        return hotelService.getEntityByCode(code);
    }

    //Alta de una nueva reserva de hotel.
    @PostMapping("/api/v1/hotel-booking/new")
    public BookingResponse bookingResponse(@RequestBody @Valid BookingRequestDto bookingRequestDto) {
        return hotelService.reservationHotel(bookingRequestDto);
    }

    @DeleteMapping("/api/v1/hotels/delete")
    // /api/v1/flights/delete?flightNumber=number
    //Baja de un hotel.
    public ErrorDTO deleteByCode(@RequestParam String code){
        return hotelService.deleteEntity(code);
    }

    @DeleteMapping("/api/v1/hotel-booking/delete")
    //Baja de una reserva de hotel
    // /api/v1/flights/delete?flightNumber=number
    public ErrorDTO deleteBookingByID(@RequestParam Integer id){return hotelService.deleteEntity(id);
    }

    //Lista de todos los hoteles y hoteles segun filtros
    @GetMapping("/api/v1/hotels")
    // /api/v1/hotels?dateFrom=dd/mm/aaaa&dateTo=dd/mm/aaaa&destination=Puerto Iguazu

    public List<HotelDTO> hotelesDisponibles(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
                                             @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo,
                                             @RequestParam(required = false) @Size(min = 3, message = "Debe ingresar un destino") String destination) {

        return hotelService.findHotelAvailable(dateFrom, dateTo, destination);
    }
    @GetMapping("/api/v1/hotel-bookings")
    //Devuelve la lista de las reservas de hoteles
    public List<BookingResponse> hotelesDisponibles() {
        return hotelService.getAllEntitiesResponse();
    }
}

