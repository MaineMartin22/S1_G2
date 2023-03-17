package com.sprint1.AgenciaDeTurismo.Service;

import com.sprint1.AgenciaDeTurismo.DTO.FlightDTO;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight.FlightDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight.FlightRequestDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PaymentMethodDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PeopleDto;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Flight.FlightResponse;
import com.sprint1.AgenciaDeTurismo.DTO.StatusCodeDto;
import com.sprint1.AgenciaDeTurismo.Exception.BadRequestException;
import com.sprint1.AgenciaDeTurismo.Exception.NotFoundException;
import com.sprint1.AgenciaDeTurismo.Exception.PaymentRequiredException;
import com.sprint1.AgenciaDeTurismo.Model.FlightModel;
import com.sprint1.AgenciaDeTurismo.Repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;

@Service
public class FlightService implements IFlightService {
    @Autowired
    FlightRepository flightRepository;
    // US 0004

    public List<FlightDto> getFlight() {
        return flightRepository.dataFlights();
    }

    // US 0005
    public List<FlightDto> getFlightAvailability(LocalDate dateFrom, LocalDate dateTo, String origin, String destination) {
        // Si no se pasan parametros, devuevle la lista completa
        if (dateFrom == null && dateTo == null && origin == null && destination == null) {
            return getFlight();
        }

        if (dateFrom == null || dateTo == null || origin == null || destination == null) {
            throw new BadRequestException("Los parametros de fecha (ida y vuelta), origen y destino no pueden estar vacios");
        }

        List<FlightDto> vueloDisponible = flightRepository.getFlightAvailability(dateFrom, dateTo, origin, destination);

        if (vueloDisponible.isEmpty()) {
            throw new NotFoundException("No se encontraron vuelos con esos datos");
        }

        return vueloDisponible;
    }

    // US 0006
    public FlightResponse reservationFlight(FlightRequestDto flightRequestDto) {
        FlightResponse response = new FlightResponse();
        FlightDTO flightResponseDto = new FlightDTO();

        if (flightRepository.dataFlights().isEmpty()) {
            throw new NotFoundException("No se encontraron vuelos disponibles");
        }

        FlightModel reservationFlight = flightRepository.findFlight(flightRequestDto.getFlightReservation().getFlightNumber());

        if (reservationFlight == null) {
            throw new NotFoundException("No se encuentro un vuelo con ese código");
        }

        List<FlightDto> reservationTrue = flightRepository.getFlightAvailability(flightRequestDto.getFlightReservation().getDateFrom(), flightRequestDto.getFlightReservation().getDateTo(),
                flightRequestDto.getFlightReservation().getOrigin(), flightRequestDto.getFlightReservation().getDestination());
        if (reservationTrue.isEmpty()) {
            throw new NotFoundException("Las fechas solicitadas no están disponibles");
        }
        PeopleDto personData = flightRequestDto.getFlightReservation().getPeople();

        if (personData.getMail() == null || personData.getDni() == null || personData.getName() == null ||
                personData.getLastName() == null || personData.getBirthDate() == null || personData.getMail().length() < 10 ||
                personData.getDni().length() < 4 || personData.getName().length() < 3 ||
                personData.getLastName().length() < 4 || personData.getBirthDate().length() < 8) {
            throw new BadRequestException("Debes ingresar correctamente los datos del huesped");


        }
        PaymentMethodDto paymentMethod = flightRequestDto.getFlightReservation().getPaymentMethod();
        if (paymentMethod.getNumber() == null || (paymentMethod.getDues() == null || paymentMethod.getDues() < 1) || paymentMethod.getType() == null) {
            throw new PaymentRequiredException("Debes ingresar un método de pago válido");
        }
        if (!paymentMethod.getType().equalsIgnoreCase("credit") && !paymentMethod.getType().equalsIgnoreCase("debit")) {
            throw new PaymentRequiredException("No se permite este metodo de pago " + paymentMethod.getType());
        }

        if (flightRequestDto.getFlightReservation().getSeats() < 1) {
            throw new NotFoundException("La cantidad de pasajeros no puede ser menor a 1.");
        }
        String origin = flightRequestDto.getFlightReservation().getOrigin();
        String destiny = flightRequestDto.getFlightReservation().getDestination();
        if (!origin.equalsIgnoreCase(reservationFlight.getOrigin()) && !destiny.equalsIgnoreCase(reservationFlight.getDestiny())) {
            throw new NotFoundException("El vuelo desde con los destinos ingresados no se encuentra disponible.");
        }

        String seatTypeDisp = reservationFlight.getSeatType();
        String seatResponse = "El tipo de asiento disponible para este vuelo es: " + seatTypeDisp + ".";
        if (!flightRequestDto.getFlightReservation().getSeatType().equalsIgnoreCase(reservationFlight.getSeatType())) {
            throw new NotFoundException("El tipo de a   siento ingresado no esta disponible." + "\n" + seatResponse);
        }


        flightResponseDto.setFlightNumber(flightRequestDto.getFlightReservation().getFlightNumber());
        flightResponseDto.setOrigin(flightRequestDto.getFlightReservation().getOrigin());
        flightResponseDto.setDestination(flightRequestDto.getFlightReservation().getDestination());
        flightResponseDto.setSeatType(flightRequestDto.getFlightReservation().getSeatType());
        flightResponseDto.setDateFrom(flightRequestDto.getFlightReservation().getDateFrom());
        flightResponseDto.setDateTo(flightRequestDto.getFlightReservation().getDateTo());
        flightResponseDto.setSeats(flightRequestDto.getFlightReservation().getSeats());
        flightResponseDto.setPeople(flightRequestDto.getFlightReservation().getPeople());


        Integer totalPrice = (int) (reservationFlight.getPriceForPerson() * flightRequestDto.getFlightReservation().getSeats());

        response.setUserName(flightRequestDto.getUserName());
        response.setTotal(totalPrice);
        response.setFlightReservation(flightResponseDto);
        response.setStatusCode(new StatusCodeDto(200, "Proceso termino satisfactoriamente"));

        return response;
    }
}
