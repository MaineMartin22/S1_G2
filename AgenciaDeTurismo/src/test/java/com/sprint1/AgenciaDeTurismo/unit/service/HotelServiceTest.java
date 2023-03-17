package com.sprint1.AgenciaDeTurismo.unit.service;

import com.sprint1.AgenciaDeTurismo.DTO.HotelDTO;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel.BookingDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel.BookingRequestDto;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Hotel.BookingResponse;
import com.sprint1.AgenciaDeTurismo.Exception.BadRequestException;
import com.sprint1.AgenciaDeTurismo.Exception.NotFoundException;
import com.sprint1.AgenciaDeTurismo.Model.HotelModel;
import com.sprint1.AgenciaDeTurismo.Repository.HotelRepository;
import com.sprint1.AgenciaDeTurismo.Service.HotelService;
import com.sprint1.AgenciaDeTurismo.utils.Hotel.BookingRequestDTOFactory;
import com.sprint1.AgenciaDeTurismo.utils.Hotel.BookingResponseFactory;
import com.sprint1.AgenciaDeTurismo.utils.Hotel.HotelDTOFactory;
import com.sprint1.AgenciaDeTurismo.utils.Hotel.HotelFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class HotelServiceTest {

    @Mock
    HotelRepository hotelRepository;

    @InjectMocks
    HotelService hotelService;

    @Test

        // US 0001 No hay hoteles disponibles
    void dataNotExist() {
        //Arrange
        BookingRequestDto param = null;

        Mockito.when(hotelRepository.dataHotels()).thenReturn(List.of());


        //Act&&Assert
        Assertions.assertThrows(NotFoundException.class, () ->
                hotelService.reservationHotel(param));

    }

    @Test
        // US 0002 No encuentra hoteles disponibles para el rango de fechas y destino seleccionados//
    void getNoHotelDisponibles() {

        // Arrange
        BookingRequestDto param1 = new BookingRequestDto(); // Initialize with a valid value
        param1.setBooking(new BookingDto());
        param1.getBooking().setDateFrom(LocalDate.of(2023, 3, 1));
        param1.getBooking().setDateTo(LocalDate.of(2023, 3, 5));
        param1.getBooking().setDestination("Buenos Aires");

        //En este caso utilizamos varios Mokitos para lograr obtener el resultado esperado
        Mockito.when(hotelRepository.dataHotels()).thenReturn(List.of(HotelDTOFactory.getBristolDTO()));
        Mockito.when(hotelRepository.findHotelWhitCode(param1.getBooking().getHotelCode())).thenReturn((HotelFactory.getBristol()));

        Mockito.when(hotelRepository.getHotelDisponible(
                param1.getBooking().getDateFrom(),
                param1.getBooking().getDateTo(),
                param1.getBooking().getDestination()
        )).thenReturn(List.of());


        // Act & Assert
        Assertions.assertThrows(BadRequestException.class, () ->
                hotelService.reservationHotel(param1));

    }

    @Test
        //US 0003 Notifica error/imposibilidad de finalizar la transacción
    void ReservationHotelNoFinally() {
        //Arrange
        BookingRequestDto param2 = new BookingRequestDto();
        param2.setBooking(new BookingDto());
        param2.getBooking().setDateFrom(LocalDate.of(2023, 3, 1));
        param2.getBooking().setDateTo(LocalDate.of(2023, 3, 5));
        param2.getBooking().setDestination("Buenos Aires");
        param2.getBooking().setRoomType("DOBLE");
        param2.getBooking().setPeopleAmount(3);

        // Act
        Mockito.when(hotelRepository.dataHotels()).thenReturn(List.of(HotelDTOFactory.getBristolDTO()));
        Mockito.when(hotelRepository.findHotelWhitCode(param2.getBooking().getHotelCode())).thenReturn(HotelFactory.getBristol());


        //Assert
        Assertions.assertThrows(NotFoundException.class, () ->
                hotelService.reservationHotel(param2));
    }

    @Test
    void findAll() {
        // arrange
        List<HotelDTO> expected = List.of(HotelDTOFactory.getCataratasHotelDTO(),
                HotelDTOFactory.getBristolDTO());
        // act
        Mockito.when(hotelRepository.dataHotels()).thenReturn(expected);
        var result = hotelService.findAll();

        // assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    void getHotelDisponibles() {
        // arrange
        List<HotelDTO> expected = List.of(HotelDTOFactory.getCataratasHotelDTO());

        LocalDate dateFrom = LocalDate.of(2022, 02, 10);
        LocalDate dateTo = LocalDate.of(2022, 02, 20);
        String destination = "Puerto Iguazú";

        List<HotelDTO> hoteles = List.of(HotelDTOFactory.getCataratasHotelDTO(),
                HotelDTOFactory.getBristolDTO());
        // act
        Mockito.when(hotelRepository.dataHotels()).thenReturn(hoteles);
        Mockito.when(hotelRepository.getHotelDisponible(dateFrom, dateTo, destination)).thenReturn(expected);
        var result = hotelService.getHotelDisponibles(dateFrom, dateTo, destination);

        // assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    void reservationHotel() {
        // arrange
        List<HotelDTO> hoteles = List.of(HotelDTOFactory.getCataratasHotelDTO(),
                HotelDTOFactory.getBristolDTO());

        List<HotelDTO> hotelDisponible = List.of(HotelDTOFactory.getCataratasHotelDTO());

        LocalDate dateFrom = LocalDate.of(2022, 02, 10);
        LocalDate dateTo = LocalDate.of(2022, 03, 20);
        String destination = "Puerto Iguazú";

        String code = "CH-0002";
        HotelModel hotelCode = HotelFactory.getCataratasHotel();

        BookingResponse expected = BookingResponseFactory.getReservationHotelIguazuDebit();

        BookingRequestDto bookingRequestDto = BookingRequestDTOFactory.bookingDtoPuertoIguazuDobleDebit();

        // act
        Mockito.when(hotelRepository.dataHotels()).thenReturn(hoteles);
        Mockito.when(hotelRepository.getHotelDisponible(dateFrom, dateTo, destination)).thenReturn(hotelDisponible);
        Mockito.when(hotelRepository.findHotelWhitCode(code)).thenReturn(hotelCode);
        var result = hotelService.reservationHotel(bookingRequestDto);

        // assert
        Assertions.assertEquals(expected, result);

    }
}


