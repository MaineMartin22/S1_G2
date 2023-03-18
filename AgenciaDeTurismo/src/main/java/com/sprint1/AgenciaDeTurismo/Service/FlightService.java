package com.sprint1.AgenciaDeTurismo.Service;

import com.sprint1.AgenciaDeTurismo.DTO.FlightDTO;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight.FlightDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight.FlightRequestDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PaymentMethodDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PeopleDto;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Flight.FlightDTOResponse;
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
        List<FlightDto> vueloDisponible = flightRepository.getFlightAvailability(dateFrom, dateTo, origin, destination);
        if (vueloDisponible.isEmpty()) {
            throw new NotFoundException("No se encontraron vuelos con esos datos");
        }

        if (!isSameOriginAndDestination(origin, destination)) {
            throw new BadRequestException("El origen y/o destino no son válidos");
        }

        if (dateFrom == null || dateTo == null || origin == null || destination == null) {
            throw new BadRequestException("Los parametros de fecha (ida y vuelta), origen y destino no pueden estar vacios");
        }


        return vueloDisponible;
    }

    private boolean isSameOriginAndDestination(String origin, String destination) {
        return getFlight().stream().anyMatch(flight -> flight.getDestiny().equalsIgnoreCase(destination) && flight.getOrigin().equalsIgnoreCase(origin));
    }

    // US 0006
    public FlightResponse reservationFlight(FlightRequestDto flightRequestDto) {
        FlightResponse response = new FlightResponse();
        FlightDTOResponse flightResponseDto = new FlightDTOResponse();

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

        PaymentMethodDto paymentMethod = flightRequestDto.getFlightReservation().getPaymentMethod();
        if (!paymentMethod.getType().equalsIgnoreCase("credit") && !paymentMethod.getType().equalsIgnoreCase("debit")) {
            throw new PaymentRequiredException("No se permite este método de pago " + paymentMethod.getType());
        }

        String origin = flightRequestDto.getFlightReservation().getOrigin();
        String destiny = flightRequestDto.getFlightReservation().getDestination();
        if (!origin.equalsIgnoreCase(reservationFlight.getOrigin()) && !destiny.equalsIgnoreCase(reservationFlight.getDestiny())) {
            throw new NotFoundException("El vuelo con el destino ingresado no se encuentra disponible.");
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
        flightResponseDto.setPeopleDto(flightRequestDto.getFlightReservation().getPeople());


        double totalPrice = (int) (reservationFlight.getPriceForPerson() * flightRequestDto.getFlightReservation().getSeats());
        double totalIntereses = 0;


        if (paymentMethod.getType().equalsIgnoreCase("credit") && paymentMethod.getDues() <= 3){
            totalIntereses = totalPrice * 0.05;
        } else if (paymentMethod.getType().equalsIgnoreCase("credit") && paymentMethod.getDues() <= 6) {
            totalIntereses = totalPrice * 0.10;
        } else if(paymentMethod.getType().equalsIgnoreCase("credit") && paymentMethod.getDues() <= 12){
            totalIntereses = totalPrice * 0.15;
        } else if ((paymentMethod.getType().equalsIgnoreCase("credit") && paymentMethod.getDues() > 12)){
            throw new PaymentRequiredException("Las cuotas con tarjeta de crédito no pueden ser mayor a 12");
        }

        if ((paymentMethod.getType().equalsIgnoreCase("debit") && paymentMethod.getDues() != 1)){
            throw new PaymentRequiredException("Solo se permite el pago en una sola cuota");
        }

        double totalFinal = totalPrice + totalIntereses;
        response.setUserName(flightRequestDto.getUserName());
        response.setTotalNeto(totalPrice);
        response.setTotalIntereses(totalIntereses);
        response.setTotalFinal(totalFinal);
        response.setFlightReservation(flightResponseDto);
        response.setStatusCode(new StatusCodeDto(200, "Proceso termino satisfactoriamente"));

        return response;
    }
}
