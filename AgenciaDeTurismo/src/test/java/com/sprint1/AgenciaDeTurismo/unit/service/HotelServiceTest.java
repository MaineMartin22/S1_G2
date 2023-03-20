package com.sprint1.AgenciaDeTurismo.unit.service;


import com.sprint1.AgenciaDeTurismo.DTO.HotelDTO;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel.BookingDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel.BookingRequestDto;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Hotel.BookingResponse;
import com.sprint1.AgenciaDeTurismo.Exception.BadRequestException;
import com.sprint1.AgenciaDeTurismo.Exception.NotFoundException;
import com.sprint1.AgenciaDeTurismo.Exception.PaymentRequiredException;
import com.sprint1.AgenciaDeTurismo.Model.HotelModel;
import com.sprint1.AgenciaDeTurismo.Repository.HotelRepository;
import com.sprint1.AgenciaDeTurismo.Service.HotelService;
import com.sprint1.AgenciaDeTurismo.utils.Hotel.BookingRequestDTOFactory;
import com.sprint1.AgenciaDeTurismo.utils.Hotel.BookingResponseFactory;
import com.sprint1.AgenciaDeTurismo.utils.Hotel.HotelDTOFactory;
import com.sprint1.AgenciaDeTurismo.utils.Hotel.HotelFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("No hay hoteles disponibles")
    void dataNotExist() {
        //Arrange
        BookingRequestDto param = null;

        Mockito.when(hotelRepository.dataHotels()).thenReturn(List.of());

        //Act&&Assert
        Assertions.assertThrows(NotFoundException.class, () ->
                hotelService.reservationHotel(param));
    }
    @Test
    @DisplayName("No encuentra hoteles disponibles para el rango de fechas y destino seleccionados")

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
    @DisplayName("Notifica error/imposibilidad de finalizar la transacción")

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
    @DisplayName("Listado de todos los hoteles")
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
    @DisplayName("Búsqueda de un hotel")
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
    @DisplayName("Solicitud de reserva de hotel")
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
    @DisplayName("Si no se pasan parámetros, devuelve listado completo de hoteles")
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
    @DisplayName("Si al menos un parámetro es nulo, lanza una excepción")
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
    @DisplayName("Si el código de hotel no existe, lanza una excepción")
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
    @DisplayName("No se encontraron hoteles disponibles para el rango de fechas seleccionadas")
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


    @Test
    @DisplayName("Ingresamos un método de pago inválido, lo que provoca que devuelva una excepción")
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
    @Test
    @DisplayName("No coincide el tipo de habitación con la cantidad de personas")

    void ReservationHotelRoomNotExist() {
        //Arrange
        BookingRequestDto param2 = BookingRequestDTOFactory.bookingDtoPuertoIguazuDobleDebit();
        List<HotelDTO> expectedDataHotels = List.of(HotelDTOFactory.getCataratasHotelDTO(),HotelDTOFactory.getBristolDTO());
        param2.getBooking().setPeopleAmount(10);
        // Act
        Mockito.when(hotelRepository.dataHotels()).thenReturn(expectedDataHotels);
        Mockito.when(hotelRepository.findHotelWhitCode(param2.getBooking().getHotelCode())).thenReturn(HotelFactory.getCataratasHotel());


        //Assert
        Assertions.assertThrows(BadRequestException.class, () ->
                hotelService.reservationHotel(param2));
    }
    @Test
    @DisplayName("Las fechas solicitadas no están disponibles")
    void ReservationHotelDateNotAvailable() {
        //Arrange
        BookingRequestDto param2 = BookingRequestDTOFactory.bookingDtoPuertoIguazuDobleDebit();
        List<HotelDTO> expectedDataHotels = List.of(HotelDTOFactory.getCataratasHotelDTO(),HotelDTOFactory.getBristolDTO());
        param2.getBooking().setDateTo(LocalDate.of(2024, 06, 03));
        // Act
        Mockito.when(hotelRepository.dataHotels()).thenReturn(expectedDataHotels);
        Mockito.when(hotelRepository.findHotelWhitCode(param2.getBooking().getHotelCode())).thenReturn(HotelFactory.getCataratasHotel());


        //Assert
        Assertions.assertThrows(BadRequestException.class, () ->
                hotelService.reservationHotel(param2));
    }
    @Test
    @DisplayName("Al ingresar una reserva con pago en mas de una cuota con debito, lanza una excepción")
    void reservationHotelDebitRefused() {
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
        bookingRequestDto.getBooking().getPaymentMethod().setDues(2);

        // act
        Mockito.when(hotelRepository.dataHotels()).thenReturn(hoteles);
        Mockito.when(hotelRepository.getHotelDisponible(dateFrom, dateTo, destination)).thenReturn(hotelDisponible);
        Mockito.when(hotelRepository.findHotelWhitCode(code)).thenReturn(hotelCode);


        // assert
        Assertions.assertThrows(PaymentRequiredException.class, () ->
                hotelService.reservationHotel(bookingRequestDto));

    }
    @Test
    @DisplayName("Al ingresar una reserva con pago en mas de doce cuotas con crédito, lanza una excepción")
    void reservationHotelCreditRefused() {
        // arrange
        List<HotelDTO> hoteles = List.of(HotelDTOFactory.getCataratasHotelDTO(),
                HotelDTOFactory.getBristolDTO());

        List<HotelDTO> hotelDisponible = List.of(HotelDTOFactory.getCataratasHotelDTO());

        LocalDate dateFrom = LocalDate.of(2022, 02, 10);
        LocalDate dateTo = LocalDate.of(2022, 03, 20);
        String destination = "Puerto Iguazú";

        String code = "CH-0002";
        HotelModel hotelCode = HotelFactory.getCataratasHotel();

        BookingRequestDto bookingRequestDto = BookingRequestDTOFactory.bookingDtoPuertoIguazuDoblegetRefused();

        // act
        Mockito.when(hotelRepository.dataHotels()).thenReturn(hoteles);
        Mockito.when(hotelRepository.getHotelDisponible(dateFrom, dateTo, destination)).thenReturn(hotelDisponible);
        Mockito.when(hotelRepository.findHotelWhitCode(code)).thenReturn(hotelCode);


        // assert
        Assertions.assertThrows(PaymentRequiredException.class, () ->
                hotelService.reservationHotel(bookingRequestDto));

    }
    @Test
    @DisplayName("Solicitud de reserva de hotel, con tarjeta de crédito")
    void reservationHotelCredit() {
        // arrange
        List<HotelDTO> hoteles = List.of(HotelDTOFactory.getCataratasHotelDTO(),
                HotelDTOFactory.getBristolDTO());

        List<HotelDTO> hotelDisponible = List.of(HotelDTOFactory.getBristolDTO());

        LocalDate dateFrom = LocalDate.of(2022, 02, 10);
        LocalDate dateTo = LocalDate.of(2022, 02, 20);
        String destination = "Buenos Aires";

        String code = HotelDTOFactory.getBristolDTO().getHotelCode();
        HotelModel hotelCode = HotelFactory.getBristol();

        BookingResponse expected = BookingResponseFactory.getReservationHotelBsAsThreeDues();
        BookingRequestDto bookingRequestDto = BookingRequestDTOFactory.bookingDtoBuenosAiresSingleThreeDues();

        // act
        Mockito.when(hotelRepository.dataHotels()).thenReturn(hoteles);
        Mockito.when(hotelRepository.getHotelDisponible(dateFrom, dateTo, destination)).thenReturn(hotelDisponible);
        Mockito.when(hotelRepository.findHotelWhitCode(code)).thenReturn(hotelCode);
        var result = hotelService.reservationHotel(bookingRequestDto);

        // assert
        Assertions.assertEquals(expected, result);

    }
    @Test
    @DisplayName("Corresponde a un pago con tarjeta de crédito superior a tres cuotas, donde se aplicará un 10% de interés")
    void reservationHotelCreditSixDues() {
        // arrange
        List<HotelDTO> hoteles = List.of(HotelDTOFactory.getCataratasHotelDTO(),
                HotelDTOFactory.getBristolDTO());

        List<HotelDTO> hotelDisponible = List.of(HotelDTOFactory.getBristolDTO());

        LocalDate dateFrom = LocalDate.of(2022, 02, 10);
        LocalDate dateTo = LocalDate.of(2022, 02, 20);
        String destination = "Buenos Aires";

        String code = HotelDTOFactory.getBristolDTO().getHotelCode();
        HotelModel hotelCode = HotelFactory.getBristol();

        BookingResponse expected = BookingResponseFactory.getReservationHotelBsAsSixDues();
        BookingRequestDto bookingRequestDto = BookingRequestDTOFactory.bookingDtoBuenosAiresSingleSixeDues();

        // act
        Mockito.when(hotelRepository.dataHotels()).thenReturn(hoteles);
        Mockito.when(hotelRepository.getHotelDisponible(dateFrom, dateTo, destination)).thenReturn(hotelDisponible);
        Mockito.when(hotelRepository.findHotelWhitCode(code)).thenReturn(hotelCode);
        var result = hotelService.reservationHotel(bookingRequestDto);

        // assert
        Assertions.assertEquals(expected, result);

    }
    @Test
    @DisplayName("Corresponde a un pago con tarjeta de crédito superior a seis cuotas, donde se aplicará un 15% de interés")
    void reservationHotelCreditTwelveDues() {
        // arrange
        List<HotelDTO> hoteles = List.of(HotelDTOFactory.getCataratasHotelDTO(),
                HotelDTOFactory.getBristolDTO());

        List<HotelDTO> hotelDisponible = List.of(HotelDTOFactory.getBristolDTO());

        LocalDate dateFrom = LocalDate.of(2022, 02, 10);
        LocalDate dateTo = LocalDate.of(2022, 02, 20);
        String destination = "Buenos Aires";

        String code = HotelDTOFactory.getBristolDTO().getHotelCode();
        HotelModel hotelCode = HotelFactory.getBristol();

        BookingResponse expected = BookingResponseFactory.getReservationHotelBsAsTwelveDues();
        BookingRequestDto bookingRequestDto = BookingRequestDTOFactory.bookingDtoBuenosAiresSingleTwelveDues();

        // act
        Mockito.when(hotelRepository.dataHotels()).thenReturn(hoteles);
        Mockito.when(hotelRepository.getHotelDisponible(dateFrom, dateTo, destination)).thenReturn(hotelDisponible);
        Mockito.when(hotelRepository.findHotelWhitCode(code)).thenReturn(hotelCode);
        var result = hotelService.reservationHotel(bookingRequestDto);

        // assert
        Assertions.assertEquals(expected, result);

    }
}


