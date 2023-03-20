package com.sprint1.AgenciaDeTurismo.unit.service;

import com.sprint1.AgenciaDeTurismo.DTO.FlightDto;
import com.sprint1.AgenciaDeTurismo.DTO.HotelDTO;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight.FlightRequestDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel.BookingDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel.BookingRequestDto;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Hotel.BookingResponse;
import com.sprint1.AgenciaDeTurismo.Exception.BadRequestException;
import com.sprint1.AgenciaDeTurismo.Exception.NotFoundException;
import com.sprint1.AgenciaDeTurismo.Exception.PaymentRequiredException;
import com.sprint1.AgenciaDeTurismo.Model.FlightModel;
import com.sprint1.AgenciaDeTurismo.Model.HotelModel;
import com.sprint1.AgenciaDeTurismo.Repository.HotelRepository;
import com.sprint1.AgenciaDeTurismo.Service.HotelService;
import com.sprint1.AgenciaDeTurismo.utils.Flight.FlightDTOFactory;
import com.sprint1.AgenciaDeTurismo.utils.Flight.FlightFactory;
import com.sprint1.AgenciaDeTurismo.utils.Flight.FlightRequestDTOFactory;
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

    @Test// US 0001 No hay hoteles disponibles

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
        BookingRequestDto entity = BookingRequestDTOFactory.getHotelesNoDisponibles();
        List<HotelDTO> hotels = List.of(HotelDTOFactory.getCataratasHotelDTO(),
                HotelDTOFactory.getBristolDTO());
        HotelModel hotelCode = HotelFactory.getBristol();

        //En este caso utilizamos varios Mokitos para lograr obtener el resultado esperado
        Mockito.when(hotelRepository.dataHotels()).thenReturn(hotels);
        Mockito.when(hotelRepository.findHotelWhitCode(entity.getBooking().getHotelCode())).thenReturn(hotelCode);

        // Act & Assert
        Assertions.assertThrows(NotFoundException.class, () ->
                hotelService.reservationHotel(entity));

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
    @Test
    void getHotelAvailabilityNull() {
        // arrange
        List<HotelDTO> hotels = List.of(HotelDTOFactory.getBristolDTO(),
                HotelDTOFactory.getCataratasHotelDTO());
        List<HotelDTO> expected = List.of(HotelDTOFactory.getBristolDTO(),
                HotelDTOFactory.getCataratasHotelDTO());
        LocalDate dateFrom =null;
        LocalDate dateTo= null;
        String destination = null;
        // act
        Mockito.when(hotelRepository.dataHotels()).thenReturn(hotels);
        var result = hotelService.getHotelDisponibles(dateFrom, dateTo, destination);
        // assert

        Assertions.assertEquals(expected, result);

    }
    @Test
    void getHotelAvailabilityNullOneParameter() {
        // arrange
        List<HotelDTO> expected = List.of(HotelDTOFactory.getCataratasHotelDTO());
        LocalDate dateFrom =LocalDate.of(2022,02,10);
        LocalDate dateTo= null;
        String destination = "Puerto Iguazú";
        // act
        Mockito.when(hotelRepository.getHotelDisponible(dateFrom, dateTo, destination)).thenReturn(expected);
        // assert
        Assertions.assertThrows(
                BadRequestException.class,
                () -> hotelService.getHotelDisponibles(dateFrom, dateTo, destination)
        );
    }
    @Test
    void reservationHotelCodeNotExist() {
        // Arrange
        List<HotelDTO> listaHotels = List.of(HotelDTOFactory.getCataratasHotelDTO(),
                HotelDTOFactory.getBristolDTO());
        BookingRequestDto request = BookingRequestDTOFactory.getHotelesNoDisponibles();
        request.getBooking().setHotelCode("CODE-INCORRECT"); //Seteamos un código incorrecto
        String code = request.getBooking().getHotelCode();
        // Act
        Mockito.when(hotelRepository.dataHotels()).thenReturn(listaHotels);
        Mockito.when(hotelRepository.findHotelWhitCode(code)).thenReturn(null);
        Assertions.assertThrows(
                NotFoundException.class,
                () -> hotelService.reservationHotel(request)
        );
    }

    @Test
        //US 0005 No se encontraron hoteles disponibles para el rango de fechas seleccionado
    void getNotExistentHoteltAvailability() {
        // arrange
        LocalDate dateFrom = LocalDate.of(2022,02,10);
        LocalDate dateTo= LocalDate.of(2022,02,20);
        String destination = "Puerto Iguazú";

        // act
        Mockito.when(hotelRepository.getHotelDisponible(dateFrom, dateTo, destination)).thenReturn(List.of());

        // assert
        Assertions.assertThrows(
                NotFoundException.class,
                () -> hotelService.getHotelDisponibles(dateFrom, dateTo, destination)
        ); //printStackTrace() <- Devuelve la exception en pantalla y vemos si es la correcta o no.
    }


    @Test // Ingresamos un metodo de pago no valido, lo que provoca que devuelva una PaymentRequiredException
    void reservationFlightPaymentNotExist() {
        // Arrange
        List<HotelDTO> listaHotels = List.of(HotelDTOFactory.getCataratasHotelDTO(),
                HotelDTOFactory.getBristolDTO());
        List<HotelDTO> requestHotel= List.of(HotelDTOFactory.getCataratasHotelDTO());

        BookingRequestDto request = BookingRequestDTOFactory.bookingDtoPuertoIguazuDobleDebit();

        String code = request.getBooking().getHotelCode();
        HotelModel expectedFindHotel = HotelFactory.getCataratasHotel();
        request.getBooking().getPaymentMethod().setType("Cash"); //Seteamos el metodo de pago incorrecto.

        LocalDate dateFrom = request.getBooking().getDateFrom();
        LocalDate dateTo = request.getBooking().getDateTo();
        String destination = request.getBooking().getDestination();

        // Act
        Mockito.when(hotelRepository.dataHotels()).thenReturn(listaHotels);
        Mockito.when(hotelRepository.findHotelWhitCode(code)).thenReturn(expectedFindHotel);
        Mockito.when(hotelRepository.getHotelDisponible(dateFrom, dateTo, destination)).thenReturn(requestHotel);
        Assertions.assertThrows(
                PaymentRequiredException.class,
                () -> hotelService.reservationHotel(request)
        );
    }
}


