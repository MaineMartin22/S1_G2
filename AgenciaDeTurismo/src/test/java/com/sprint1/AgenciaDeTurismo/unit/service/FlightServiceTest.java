package com.sprint1.AgenciaDeTurismo.unit.service;


import com.sprint1.AgenciaDeTurismo.DTO.FlightDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight.FlightRequestDto;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Flight.FlightResponseDTO;
import com.sprint1.AgenciaDeTurismo.Exception.BadRequestException;
import com.sprint1.AgenciaDeTurismo.Exception.NotFoundException;
import com.sprint1.AgenciaDeTurismo.Exception.PaymentRequiredException;
import com.sprint1.AgenciaDeTurismo.Entity.Flight;
import com.sprint1.AgenciaDeTurismo.Service.FlightService;
import com.sprint1.AgenciaDeTurismo.utils.Flight.*;
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
class FlightServiceTest {

    @Mock
    FlightRepository flightRepository;

    @InjectMocks
    FlightService flightService;

    // US 0004
    @Test
    @DisplayName("Devuelve el listado de todos los vuelos")
    void getFlight() {
        // arrange
        List<FlightDto> expected = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO(),
                FlightDTOFactory.getPuertoIguazuBogotaDTO());
        // act
        Mockito.when(flightRepository.dataFlights()).thenReturn(expected);
        var result = flightService.getFlight();

        // assert
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Al enviar todos los parámetros nulos, retorna la lista de vuelos")
    void getFlightAvailabilityNull() {
        // arrange
        List<FlightDto> vuelos = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO(),
                FlightDTOFactory.getPuertoIguazuBogotaDTO());
        List<FlightDto> expected = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO(),
                FlightDTOFactory.getPuertoIguazuBogotaDTO());
        LocalDate dateFrom =null;
        LocalDate dateTo= null;
        String origin= null;
        String destination = null;

        // act
        Mockito.when(flightRepository.dataFlights()).thenReturn(vuelos);
        var result = flightService.getFlightAvailability(dateFrom, dateTo, origin, destination);

        // assert
        Assertions.assertEquals(expected, result);
    }


    // US 0005
    @Test
    @DisplayName("Con los datos ingresados, verifica vuelos disponibles")
    void getFlightAvailability() {
        // arrange
        List<FlightDto> vuelos = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO(),
                FlightDTOFactory.getPuertoIguazuBogotaDTO());


        List<FlightDto> expected = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO());
        LocalDate dateFrom = LocalDate.of(2022,02,10);
        LocalDate dateTo= LocalDate.of(2022,02,15);
        String origin= "Buenos Aires";
        String destination = "Puerto Iguazú";

        // act
        Mockito.when(flightRepository.getFlightAvailability(dateFrom, dateTo, origin, destination)).thenReturn(expected);
        Mockito.when(flightRepository.dataFlights()).thenReturn(vuelos);
        var result = flightService.getFlightAvailability(dateFrom, dateTo, origin, destination);
        // assert

        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Al no pasar un dato o el mismo sea null, lanza una excepción")
    void getFlightAvailabilityNullOneParameter() {
        // arrange
        List<FlightDto> expected = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO());
        LocalDate dateFrom =LocalDate.of(2022,02,10);
        LocalDate dateTo= null;
        String origin= "Buenos Aires";
        String destination = "Puerto Iguazú";
        // act
        Mockito.when(flightRepository.getFlightAvailability(dateFrom, dateTo, origin, destination)).thenReturn(expected);
        // assert
        Assertions.assertThrows(
                BadRequestException.class,
                () -> flightService.getFlightAvailability(dateFrom, dateTo, origin, destination)
        );
    }
    @Test
    @DisplayName("Al ingresar origen o destino que no existen, devuelve una excepción")
    void getFlightAvailabilityOrigenNotExist() {
        // arrange
        List<FlightDto> expected = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO());
        LocalDate dateFrom =LocalDate.of(2022,02,10);
        LocalDate dateTo=LocalDate.of(2022,02,15);
        String origin= "Brasil";
        String destination = "Canada";
        // act
        Mockito.when(flightRepository.getFlightAvailability(dateFrom, dateTo, origin, destination)).thenReturn(expected);
        // assert
        Assertions.assertThrows(
                BadRequestException.class,
                () -> flightService.getFlightAvailability(dateFrom, dateTo, origin, destination)
        );
    }
    @Test
    @DisplayName("No se encontraron vuelos disponibles para el rango de fechas seleccionadas")
    void getNotExistentFlightAvailability() {
        // arrange
        LocalDate dateFrom = LocalDate.of(2022,02,10);
        LocalDate dateTo= LocalDate.of(2022,02,20);
        String origin= "Buenos Aires";
        String destination = "Puerto Iguazú";

        // act
        Mockito.when(flightRepository.getFlightAvailability(dateFrom, dateTo, origin, destination)).thenReturn(List.of());

        // assert
        Assertions.assertThrows(
                NotFoundException.class,
                () -> flightService.getFlightAvailability(dateFrom, dateTo, origin, destination)
        ); //printStackTrace() <- Devuelve la exception en pantalla y vemos si es la correcta o no.
    }

    @Test
    @DisplayName("Notifica la no existencia de un vuelo mediante una excepción")
    void getFlightNoExist(){
        //Arrange
        FlightRequestDto param = null;

        Mockito.when(flightRepository.dataFlights()).thenReturn(List.of());

        //Act&&Assert
        Assertions.assertThrows(NotFoundException.class, () ->
                flightService.reservationFlight(param));

    }

    // US 0006
    @Test
    @DisplayName("Verifica la reserva del vuelo con tarjeta de débito")
    void reservationFlightDebit() {
        // Arrange
        List<FlightDto> listaVuelos = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO(),
                FlightDTOFactory.getPuertoIguazuBogotaDTO());
        List<FlightDto> buscarVuelo = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO());
        LocalDate dateFrom = LocalDate.of(2022,02,10);
        LocalDate dateTo= LocalDate.of(2022,02,15);
        String origin= "Buenos Aires";
        String destination = "Puerto Iguazú";
        String code = FlightFactory.getBsAsPuertoIguazu().getNumberFlight();
        Flight returnCodigoVuelo = FlightFactory.getBsAsPuertoIguazu();
        FlightResponseDTO expected = FlightResponseFactory.flightDTOResponseDebitBAPI(); // FlightDTOResponseFactory
        FlightRequestDto param = FlightRequestDTOFactory.getReservationDebitBAPI(); // FlightReservationDTOFactory
        // Act
        Mockito.when(flightRepository.dataFlights()).thenReturn(listaVuelos);
        Mockito.when(flightRepository.findFlight(code)).thenReturn(returnCodigoVuelo);
        Mockito.when(flightRepository.getFlightAvailability(dateFrom, dateTo, origin, destination)).thenReturn(buscarVuelo);
        var result = flightService.reservationFlight(param);
        // Assert
        Assertions.assertEquals(expected, result);

    }
    @Test
    @DisplayName("Al ingresar erróneamente el código del vuelo en el request, lanza una excepción")
    void reservationFlightCodeNotExist() {
        // Arrange
        List<FlightDto> listaVuelos = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO(),
                FlightDTOFactory.getPuertoIguazuBogotaDTO());
        FlightRequestDto request = FlightRequestDTOFactory.getReservationDebitBAPI();
        request.getFlightReservation().setFlightNumber("CODE-INCORRECT"); //Seteamos un código incorrecto
        String code = request.getFlightReservation().getFlightNumber();
        // Act
        Mockito.when(flightRepository.dataFlights()).thenReturn(listaVuelos);
        Mockito.when(flightRepository.findFlight(code)).thenReturn(null);
        Assertions.assertThrows(
                NotFoundException.class,
                () -> flightService.reservationFlight(request)
        );
    }
    @Test
    @DisplayName("Al ingresar un método de pago invalido, lanza una excepción")
    void reservationFlightPaymentNotExist() {
        // Arrange
        List<FlightDto> listaVuelos = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO(),
                FlightDTOFactory.getPuertoIguazuBogotaDTO());
        List<FlightDto> requestFlightAvailability = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO());

        FlightRequestDto request = FlightRequestDTOFactory.getReservationDebitBAPI();

        String code = request.getFlightReservation().getFlightNumber();
        Flight expectedFindFlight = FlightFactory.getBsAsPuertoIguazu();
        request.getFlightReservation().getPaymentMethod().setType("Cash"); //Seteamos el metodo de pago incorrecto.

        LocalDate dateFrom = request.getFlightReservation().getDateFrom();
        LocalDate dateTo = request.getFlightReservation().getDateTo();
        String origin = request.getFlightReservation().getOrigin();
        String destination = request.getFlightReservation().getDestination();

        // Act
        Mockito.when(flightRepository.dataFlights()).thenReturn(listaVuelos);
        Mockito.when(flightRepository.findFlight(code)).thenReturn(expectedFindFlight);
        Mockito.when(flightRepository.getFlightAvailability(dateFrom, dateTo, origin, destination)).thenReturn(requestFlightAvailability);
        Assertions.assertThrows(
                PaymentRequiredException.class,
                () -> flightService.reservationFlight(request)
        );
    }

    @Test
    @DisplayName("Al ingresar un tipo de asiento no disponible, lanza una excepción")
    void reservationFlightSeatNotAvailable() {
        // Arrange
        List<FlightDto> listaVuelos = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO(),
                FlightDTOFactory.getPuertoIguazuBogotaDTO());
        List<FlightDto> requestFlightAvailability = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO());

        FlightRequestDto request = FlightRequestDTOFactory.getReservationDebitBAPI();

        String code = request.getFlightReservation().getFlightNumber();
        Flight expectedFindFlight = FlightFactory.getBsAsPuertoIguazu();

        LocalDate dateFrom = request.getFlightReservation().getDateFrom();
        LocalDate dateTo = request.getFlightReservation().getDateTo();
        String origin = request.getFlightReservation().getOrigin();
        String destination = request.getFlightReservation().getDestination();
        request.getFlightReservation().setSeatType("Business"); // Seteamos mal el tipo de asiento.
        // Act
        Mockito.when(flightRepository.dataFlights()).thenReturn(listaVuelos);
        Mockito.when(flightRepository.findFlight(code)).thenReturn(expectedFindFlight);
        Mockito.when(flightRepository.getFlightAvailability(dateFrom, dateTo, origin, destination)).thenReturn(requestFlightAvailability);
        Assertions.assertThrows(
                NotFoundException.class,
                () -> flightService.reservationFlight(request)
        );
    }

    @Test
    @DisplayName("Al ingresar una reserva con pago en mas de una cuota con debito, lanza una PaymentRequiredException")
    void reservationFlightTypeDebitRefused() {
        // Arrange
        List<FlightDto> listaVuelos = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO(),
                FlightDTOFactory.getPuertoIguazuBogotaDTO());
        List<FlightDto> requestFlightAvailability = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO());

        FlightRequestDto request = FlightRequestDTOFactory.getReservationDebitBAPI();

        String code = request.getFlightReservation().getFlightNumber();
        Flight expectedFindFlight = FlightFactory.getBsAsPuertoIguazu();

        LocalDate dateFrom = request.getFlightReservation().getDateFrom();
        LocalDate dateTo = request.getFlightReservation().getDateTo();
        String origin = request.getFlightReservation().getOrigin();
        String destination = request.getFlightReservation().getDestination();
        request.getFlightReservation().getPaymentMethod().setDues(2);
        // Act
        Mockito.when(flightRepository.dataFlights()).thenReturn(listaVuelos);
        Mockito.when(flightRepository.findFlight(code)).thenReturn(expectedFindFlight);
        Mockito.when(flightRepository.getFlightAvailability(dateFrom, dateTo, origin, destination)).thenReturn(requestFlightAvailability);
        Assertions.assertThrows(
                PaymentRequiredException.class,
                () -> flightService.reservationFlight(request)
        );
    }

    @Test
    @DisplayName("Notifica error/imposibilidad de finalizar la transacción")
    void ReservationFlightNoFinally() {
        //Arrange
        FlightRequestDto param2 = FlightRequestDTOFactory.getNoReservation();
        // Act
        Mockito.when(flightRepository.dataFlights()).thenReturn(List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO()));
        Mockito.when(flightRepository.findFlight(param2.getFlightReservation().getFlightNumber())).thenReturn(FlightFactory.getBsAsPuertoIguazu());
        //Assert
        Assertions.assertThrows(NotFoundException.class, () ->
                flightService.reservationFlight(param2));
    }

    @Test
    @DisplayName("Al pagar con tarjeta de crédito en 3 cuotas o menos, agrega un 5% al valor del vuelo")
    void reservationFlightCredit() {
        // Arrange
        List<FlightDto> listaVuelos = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO(),
                FlightDTOFactory.getPuertoIguazuBogotaDTO());
        List<FlightDto> buscarVuelo = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO());
        LocalDate dateFrom = LocalDate.of(2022,02,10);
        LocalDate dateTo= LocalDate.of(2022,02,15);
        String origin= "Buenos Aires";
        String destination = "Puerto Iguazú";
        String code = FlightFactory.getBsAsPuertoIguazu().getNumberFlight();
        Flight returnCodigoVuelo = FlightFactory.getBsAsPuertoIguazu();
        FlightResponseDTO expected = FlightResponseFactory.flightDTOResponseCreditThreeBAPI(); // FlightDTOResponseFactory
        FlightRequestDto param = FlightRequestDTOFactory.getReservationCreditThreeBAPI(); // FlightReservationDTOFactory
        // Act
        Mockito.when(flightRepository.dataFlights()).thenReturn(listaVuelos);
        Mockito.when(flightRepository.findFlight(code)).thenReturn(returnCodigoVuelo);
        Mockito.when(flightRepository.getFlightAvailability(dateFrom, dateTo, origin, destination)).thenReturn(buscarVuelo);
        var result = flightService.reservationFlight(param);
        // Assert
        Assertions.assertEquals(expected, result);

    }
    @Test
    @DisplayName("Al pagar con tarjeta de crédito en 4 cuotas o hasta 6, agrega un 10% al valor del vuelo")
    void reservationFlightCreditSixDues() {
        // Arrange
        List<FlightDto> listaVuelos = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO(),
                FlightDTOFactory.getPuertoIguazuBogotaDTO());
        List<FlightDto> returnFlightAvailability = List.of(FlightDTOFactory.getPuertoIguazuBogotaDTO());
        LocalDate dateFrom = LocalDate.of(2022,02,10);
        LocalDate dateTo= LocalDate.of(2022,02,20);
        String origin= "Puerto Iguazú";
        String destination = "Bogotá";
        String code = FlightFactory.getPuertoIguazuBogota().getNumberFlight();
        Flight returnCodigoVuelo = FlightFactory.getPuertoIguazuBogota();
        FlightResponseDTO expected = FlightResponseFactory.flightDTOResponseCreditSixPIBA(); // FlightDTOResponseFactory
        FlightRequestDto param = FlightRequestDTOFactory.getReservationCreditSixPIBA(); // FlightReservationDTOFactory
        // Act
        Mockito.when(flightRepository.dataFlights()).thenReturn(listaVuelos);
        Mockito.when(flightRepository.findFlight(code)).thenReturn(returnCodigoVuelo);
        Mockito.when(flightRepository.getFlightAvailability(dateFrom, dateTo, origin, destination)).thenReturn(returnFlightAvailability);
        var result = flightService.reservationFlight(param);
        // Assert
        Assertions.assertEquals(expected, result);

    }
    @Test
    @DisplayName("Al pagar con tarjeta de crédito en 7 cuotas o hasta 12, agrega un 15% al valor del vuelo")
    void reservationFlightCreditTwelveDues() {
        // Arrange
        List<FlightDto> listaVuelos = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO(),
                FlightDTOFactory.getPuertoIguazuBogotaDTO());
        List<FlightDto> returnFlightAvailability = List.of(FlightDTOFactory.getPuertoIguazuBogotaDTO());
        LocalDate dateFrom = LocalDate.of(2022,02,10);
        LocalDate dateTo= LocalDate.of(2022,02,20);
        String origin= "Puerto Iguazú";
        String destination = "Bogotá";
        String code = FlightFactory.getPuertoIguazuBogota().getNumberFlight();
        Flight returnCodigoVuelo = FlightFactory.getPuertoIguazuBogota();
        FlightResponseDTO expected = FlightResponseFactory.flightDTOResponseCreditTwelvePIBA(); // FlightDTOResponseFactory
        FlightRequestDto param = FlightRequestDTOFactory.getReservationCreditTwelvePIBA(); // FlightReservationDTOFactory
        // Act
        Mockito.when(flightRepository.dataFlights()).thenReturn(listaVuelos);
        Mockito.when(flightRepository.findFlight(code)).thenReturn(returnCodigoVuelo);
        Mockito.when(flightRepository.getFlightAvailability(dateFrom, dateTo, origin, destination)).thenReturn(returnFlightAvailability);
        var result = flightService.reservationFlight(param);
        // Assert
        Assertions.assertEquals(expected, result);

    }
 @Test
 @DisplayName("Si el tipo de pago es credit y excede el límite de cuotas que está permitido, lanza una Excepción")
 void reservationFlightTypeCreditRefused() {
     // Arrange
     List<FlightDto> listaVuelos = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO(),
             FlightDTOFactory.getPuertoIguazuBogotaDTO());
     List<FlightDto> requestFlightAvailability = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO());

     FlightRequestDto request = FlightRequestDTOFactory.getReservationDebitBAPI();

     String code = request.getFlightReservation().getFlightNumber();
     Flight expectedFindFlight = FlightFactory.getBsAsPuertoIguazu();

     LocalDate dateFrom = request.getFlightReservation().getDateFrom();
     LocalDate dateTo = request.getFlightReservation().getDateTo();
     String origin = request.getFlightReservation().getOrigin();
     String destination = request.getFlightReservation().getDestination();
     request.getFlightReservation().getPaymentMethod().setType("credit"); // Seteamos el metodo de pago credit.
     request.getFlightReservation().getPaymentMethod().setDues(13); // Seteamos un número mayor al que está permitido el pago en cuotas

     // Act
     Mockito.when(flightRepository.dataFlights()).thenReturn(listaVuelos);
     Mockito.when(flightRepository.findFlight(code)).thenReturn(expectedFindFlight);
     Mockito.when(flightRepository.getFlightAvailability(dateFrom, dateTo, origin, destination)).thenReturn(requestFlightAvailability);
     Assertions.assertThrows(
             PaymentRequiredException.class,
             () -> flightService.reservationFlight(request)
     );
 }
}