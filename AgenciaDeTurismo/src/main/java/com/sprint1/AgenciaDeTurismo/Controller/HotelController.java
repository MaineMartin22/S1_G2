package com.sprint1.AgenciaDeTurismo.Controller;

import com.sprint1.AgenciaDeTurismo.DTO.ErrorDTO;
import com.sprint1.AgenciaDeTurismo.DTO.FlightDto;
import com.sprint1.AgenciaDeTurismo.DTO.HotelDTO;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel.BookingRequestDto;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Hotel.BookingResponseDTO;

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

    // Búsqueda de un hotel por su código.
    @GetMapping("/api/v1/hotels/findOneWhit")
    public HotelDTO findHotelByCode(@RequestParam String code) {
        return hotelService.getEntityByCode(code);
    }

    //Alta de una nueva reserva de hotel.
    @PostMapping("/api/v1/hotel-booking/new")
    public BookingResponseDTO bookingResponse(@RequestBody @Valid BookingRequestDto bookingRequestDto) {
        return hotelService.reservationHotel(bookingRequestDto);
    }

    //Baja de un hotel.
    @DeleteMapping("/api/v1/hotels/delete")
    public ErrorDTO deleteByCode(@RequestParam String code){
        return hotelService.deleteEntity(code);
    }

    //Baja de una reserva de hotel.
    @DeleteMapping("/api/v1/hotel-booking/delete")
    public ErrorDTO deleteBookingByID(@RequestParam Integer id){return hotelService.deleteReservaEntity(id);
    }

    //Lista de todos los hoteles y hoteles según filtros.
    @GetMapping("/api/v1/hotels")
    public List<HotelDTO> hotelesDisponibles(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
                                             @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo,
                                             @RequestParam(required = false) @Size(min = 3, message = "Debe ingresar un destino") String destination) {

        return hotelService.findHotelAvailable(dateFrom, dateTo, destination);
    }
    //Devuelve un listado de hoteles disponibles según destino.
    @GetMapping("/api/v1/hotels/{city}")
    public List<HotelDTO> hotelDisponiblePorCiudad(@PathVariable String city) {

        return hotelService.getAllEntitiesByCity(city);
    }

    //Devuelve la lista de las reservas de hoteles.
    @GetMapping("/api/v1/hotel-bookings")
    public List<BookingResponseDTO> reservasEnLaDB() {
        return hotelService.getAllEntitiesResponse();
    }

    // Actualiza un hotel.
    @PutMapping("/api/v1/hotels/edit")
    public HotelDTO updateHotel(@RequestParam String code, @RequestBody HotelDTO hotelDTO) {
        return hotelService.updateEntity(hotelDTO, code);
    }

    // Actualiza una reserva de hotel.
    @PutMapping("/api/v1/hotel-booking/edit")
    public BookingResponseDTO updateReservaFlight(@RequestParam Integer id,@RequestBody BookingResponseDTO bookingResponseDTO) {
        return hotelService.updateReservaEntity(bookingResponseDTO, id);
    }

    //Devuelve el precio total de las reservas
    @GetMapping("/api/v1/hotel-bookings/ForPrice")
    public List<BookingResponseDTO> reservasMontoTotal(@RequestParam Double desde, @RequestParam Double hasta) {
        return hotelService.getAllEntitiesForPrice(desde, hasta);
    }

}

