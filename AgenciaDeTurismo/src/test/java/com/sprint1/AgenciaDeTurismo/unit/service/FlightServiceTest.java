package com.sprint1.AgenciaDeTurismo.unit.service;

import com.sprint1.AgenciaDeTurismo.DTO.FlightDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight.FlightRequestDto;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Flight.FlightResponse;
import com.sprint1.AgenciaDeTurismo.Exception.BadRequestException;
import com.sprint1.AgenciaDeTurismo.Exception.NotFoundException;
import com.sprint1.AgenciaDeTurismo.Exception.PaymentRequiredException;
import com.sprint1.AgenciaDeTurismo.Model.FlightModel;
import com.sprint1.AgenciaDeTurismo.Repository.FlightRepository;
import com.sprint1.AgenciaDeTurismo.Service.FlightService;
import com.sprint1.AgenciaDeTurismo.utils.Flight.*;
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
class FlightServiceTest {

    @Mock
    FlightRepository flightRepository;

    @InjectMocks
    FlightService flightService;

    @Test
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
    //US 0004 Notifica la no existencia mediante una excepción.
    void getFlightNoExist(){
        //Arrange
        FlightRequestDto param = null;

        Mockito.when(flightRepository.dataFlights()).thenReturn(List.of());

        //Act&&Assert
        Assertions.assertThrows(NotFoundException.class, () ->
                flightService.reservationFlight(param));

    }

    @Test

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
    @Test // Al no enviar ninguno de los datos(es decir todos son nulos) debe retornar la lista de vuelos.
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
    @Test // En este caso al no pasar un dato o el mismo sea null, va a dar una BadRequestException Linea 46 FlightService.
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
    @Test // Al ingresar origen o destino que no existen, debe devolver una BadRequestException
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
    //US 0005 No se encontraron vuelos disponibles para el rango de fechas seleccionado
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
        FlightModel returnCodigoVuelo = FlightFactory.getBsAsPuertoIguazu();
        FlightResponse expected = FlightResponseFactory.flightDTOResponseDebitBAPI(); // FlightDTOResponseFactory
        FlightRequestDto param = FlightRequestDTOFactory.getReservationDebitBAPI(); // FlightReservationDTOFactory
        // Act
        Mockito.when(flightRepository.dataFlights()).thenReturn(listaVuelos);
        Mockito.when(flightRepository.findFlight(code)).thenReturn(returnCodigoVuelo);
        Mockito.when(flightRepository.getFlightAvailability(dateFrom, dateTo, origin, destination)).thenReturn(buscarVuelo);
        var result = flightService.reservationFlight(param);
        // Assert
        Assertions.assertEquals(expected, result);

    }
    @Test // Ingresamos mal el codigo del vuelo en el request, lo que provoca que devuelva una NotFoundException
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
    @Test // Ingresamos un metodo de pago no valido, lo que provoca que devuelva una PaymentRequiredException
    void reservationFlightPaymentNotExist() {
        // Arrange
        List<FlightDto> listaVuelos = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO(),
                FlightDTOFactory.getPuertoIguazuBogotaDTO());
        List<FlightDto> requestFlightAvailability = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO());

        FlightRequestDto request = FlightRequestDTOFactory.getReservationDebitBAPI();

        String code = request.getFlightReservation().getFlightNumber();
        FlightModel expectedFindFlight = FlightFactory.getBsAsPuertoIguazu();
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

    @Test // Ingresamos un tipo de asiento no disponible, lo que provoca que devuelva una NotFoundException
    void reservationFlightSeatNotAvailable() {
        // Arrange
        List<FlightDto> listaVuelos = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO(),
                FlightDTOFactory.getPuertoIguazuBogotaDTO());
        List<FlightDto> requestFlightAvailability = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO());

        FlightRequestDto request = FlightRequestDTOFactory.getReservationDebitBAPI();

        String code = request.getFlightReservation().getFlightNumber();
        FlightModel expectedFindFlight = FlightFactory.getBsAsPuertoIguazu();

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

    @Test // Cuando ingresamos una reserva con pago en debito en mas de una cuota tira una PaymentRequiredException
    void reservationFlightTypeDebitRefused() {
        // Arrange
        List<FlightDto> listaVuelos = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO(),
                FlightDTOFactory.getPuertoIguazuBogotaDTO());
        List<FlightDto> requestFlightAvailability = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO());

        FlightRequestDto request = FlightRequestDTOFactory.getReservationDebitBAPI();

        String code = request.getFlightReservation().getFlightNumber();
        FlightModel expectedFindFlight = FlightFactory.getBsAsPuertoIguazu();

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
    //US 0006 Notifica error/imposibilidad de finalizar la transacción
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

    @Test// En este metodo probamos que aplique el interes al pagar con tarjeta de credito, (Agrega un 5% al valor del vuelo)
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
        FlightModel returnCodigoVuelo = FlightFactory.getBsAsPuertoIguazu();
        FlightResponse expected = FlightResponseFactory.flightDTOResponseCreditThreeBAPI(); // FlightDTOResponseFactory
        FlightRequestDto param = FlightRequestDTOFactory.getReservationCreditThreeBAPI(); // FlightReservationDTOFactory
        // Act
        Mockito.when(flightRepository.dataFlights()).thenReturn(listaVuelos);
        Mockito.when(flightRepository.findFlight(code)).thenReturn(returnCodigoVuelo);
        Mockito.when(flightRepository.getFlightAvailability(dateFrom, dateTo, origin, destination)).thenReturn(buscarVuelo);
        var result = flightService.reservationFlight(param);
        // Assert
        Assertions.assertEquals(expected, result);

    }
    @Test// En este metodo probamos que aplique el interes al exceder la 3 cuotas, (Agrega un 10% al valor del vuelo)
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
        FlightModel returnCodigoVuelo = FlightFactory.getPuertoIguazuBogota();
        FlightResponse expected = FlightResponseFactory.flightDTOResponseCreditSixPIBA(); // FlightDTOResponseFactory
        FlightRequestDto param = FlightRequestDTOFactory.getReservationCreditSixPIBA(); // FlightReservationDTOFactory
        // Act
        Mockito.when(flightRepository.dataFlights()).thenReturn(listaVuelos);
        Mockito.when(flightRepository.findFlight(code)).thenReturn(returnCodigoVuelo);
        Mockito.when(flightRepository.getFlightAvailability(dateFrom, dateTo, origin, destination)).thenReturn(returnFlightAvailability);
        var result = flightService.reservationFlight(param);
        // Assert
        Assertions.assertEquals(expected, result);

    }
    @Test // En este metodo probamos que aplique el interes al exceder la 6 cuotas, (Agrega un 15% al valor del vuelo)
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
        FlightModel returnCodigoVuelo = FlightFactory.getPuertoIguazuBogota();
        FlightResponse expected = FlightResponseFactory.flightDTOResponseCreditTwelvePIBA(); // FlightDTOResponseFactory
        FlightRequestDto param = FlightRequestDTOFactory.getReservationCreditTwelvePIBA(); // FlightReservationDTOFactory
        // Act
        Mockito.when(flightRepository.dataFlights()).thenReturn(listaVuelos);
        Mockito.when(flightRepository.findFlight(code)).thenReturn(returnCodigoVuelo);
        Mockito.when(flightRepository.getFlightAvailability(dateFrom, dateTo, origin, destination)).thenReturn(returnFlightAvailability);
        var result = flightService.reservationFlight(param);
        // Assert
        Assertions.assertEquals(expected, result);

    }
 @Test // Al cambiar el tipo de pago a credit y exceder el límite de cutoas que está permitido tira un PaymentRequiredException
 void reservationFlightTypeCreditRefused() {
     // Arrange
     List<FlightDto> listaVuelos = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO(),
             FlightDTOFactory.getPuertoIguazuBogotaDTO());
     List<FlightDto> requestFlightAvailability = List.of(FlightDTOFactory.getBsAsPuertoIguazuDTO());

     FlightRequestDto request = FlightRequestDTOFactory.getReservationDebitBAPI();

     String code = request.getFlightReservation().getFlightNumber();
     FlightModel expectedFindFlight = FlightFactory.getBsAsPuertoIguazu();

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