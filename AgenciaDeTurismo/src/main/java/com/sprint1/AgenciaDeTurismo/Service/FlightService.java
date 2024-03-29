package com.sprint1.AgenciaDeTurismo.Service;

import com.sprint1.AgenciaDeTurismo.DTO.ErrorDTO;
import com.sprint1.AgenciaDeTurismo.DTO.FlightDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight.FlightRequestDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PaymentMethodDto;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Flight.FlightResponseDTO;
import com.sprint1.AgenciaDeTurismo.Entity.ReservationFlight;
import com.sprint1.AgenciaDeTurismo.Entity.ReservationFlightDetails;
import com.sprint1.AgenciaDeTurismo.Exception.BadRequestException;
import com.sprint1.AgenciaDeTurismo.Exception.NotFoundException;
import com.sprint1.AgenciaDeTurismo.Entity.Flight;

import com.sprint1.AgenciaDeTurismo.Repository.IFlightRepository;
import com.sprint1.AgenciaDeTurismo.Repository.IReservationFlight;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightService implements IFlightService {
    @Autowired
    IFlightRepository flightRepository;

    @Autowired
    IReservationFlight reservationFlight;

    ModelMapper mapper = new ModelMapper();

    public List<FlightDto> findFlightAvailable(LocalDate dateFrom, LocalDate dateTo, String origin, String destiny) {
        if (dateFrom == null && dateTo == null && destiny == null && origin == null) {
            return getAllEntities();
        }
        if (dateFrom == null || dateTo == null || destiny == null || origin == null) {
            throw new BadRequestException("Los datos no están completos");
        }
        var list = flightRepository.findFlightByDateFromAndDateToAndOriginAndDestiny(dateFrom, dateTo, origin, destiny);

        if (list.isEmpty())
            throw new NotFoundException("NO se encontró vuelos con esos datos");

        return list.stream().map(
                flight -> mapper.map(flight, FlightDto.class)
        ).collect(Collectors.toList());
    }

    @Override
    public FlightDto saveEntity(FlightDto objectDTO) {
        // mappear de dto a entity para llevar al repo
        var entity = mapper.map(objectDTO, Flight.class);

        // guardar
        flightRepository.save(entity);

        // mappear de entity a dto para llevar al controller

        return mapper.map(entity, FlightDto.class);
    }

    @Override
    public List<FlightDto> getAllEntities() {
        // buscar todos los resultados en el repo
        var list = flightRepository.findAll();
        // luego convertir de entidad a DTO
        return list.stream().map(
                        flight -> mapper.map(flight, FlightDto.class)
                )
                .collect(Collectors.toList());
    }

    @Override
    public FlightDto updateEntity(FlightDto flightDto, String code) {
        var flight = getEntityByCode(code);
        if (flight.getId().equals(flightDto.getId())) {
            var entity = mapper.map(flightDto, Flight.class);
            flightRepository.save(entity);
            return mapper.map(entity, FlightDto.class);
        } else
            throw new NotFoundException("No existe vuelo con ese ID");
    }

    @Override
    public FlightResponseDTO updateReservaEntity(FlightResponseDTO flightResponseDTO, Integer id) {
        if (id.equals(flightResponseDTO.getId())) {
            var entity = mapper.map(flightResponseDTO, ReservationFlight.class);
            reservationFlight.save(entity);
            return mapper.map(entity, FlightResponseDTO.class);
        } else
            throw new NotFoundException("No existe reserva con ese ID");
    }


    @Override
    public FlightDto getEntityByCode(String code) {
        Flight flight = flightRepository.findFlightByNumberFlight(code)
                .orElseThrow(() -> {
                    throw new NotFoundException("NO encontre ningún vuelo con ese código");
                });
        // mapeo de entidad a dto.
        return mapper.map(flight, FlightDto.class);
    }

    @Override
    public ErrorDTO deleteEntity(String code) {
        Flight flight = flightRepository.findFlightByNumberFlight(code)
                .orElseThrow(() -> {
                    throw new NotFoundException("NO encontre ningún vuelo con ese código");
                });

        flightRepository.delete(flight);

        return ErrorDTO.builder()
                .explanation("ELIMINACIÓN")
                .messages(List.of("Se elimino el vuelo con código " + code))
                .build();
    }

    // se elimina una reserva por id.
    @Override
    public ErrorDTO deleteReservaEntity(Integer id) {
        ReservationFlight reservation = reservationFlight.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException("NO encontre ninguna reserva con ese id");
                });

        reservationFlight.delete(reservation);

        return ErrorDTO.builder()
                .explanation("ELIMINACIÓN")
                .messages(List.of("Se elimino la reserva con id " + reservation.getId()))
                .build();
    }

    @Override
    public List<FlightResponseDTO> getAllEntitiesResponse() {
        // buscar todos los resultados en el repo
        var list = reservationFlight.findAll();
        // luego convertir de entidad a DTO
        return list.stream().map(
                        flight -> mapper.map(flight, FlightResponseDTO.class)
                )
                .collect(Collectors.toList());
    }

    @Override
    public FlightResponseDTO reservationFlight(FlightRequestDto flightRequestDto) {
        ReservationFlight response = new ReservationFlight();

        if (flightRepository.findAll().isEmpty()) {
            throw new NotFoundException("No se encontraron vuelos disponibles");
        }

        FlightDto reservation = getEntityByCode(flightRequestDto.getFlightReservation().getFlightNumber());

        findFlightAvailable(flightRequestDto.getFlightReservation().getDateFrom(),
                flightRequestDto.getFlightReservation().getDateTo(),
                flightRequestDto.getFlightReservation().getOrigin(),
                flightRequestDto.getFlightReservation().getDestiny());

        if (!flightRequestDto.getFlightReservation().getSeatType().equalsIgnoreCase(reservation.getSeatType())) {
            throw new NotFoundException("El tipo de asiento ingresado no esta disponible." + "\n"
                    + "El tipo de asiento disponible para este vuelo es: " + reservation.getSeatType() + ".");
        }

        var flightResponseDto = mapper.map(flightRequestDto.getFlightReservation(), ReservationFlightDetails.class);

        double totalPrice = reservation.getPriceForPerson() * flightRequestDto.getFlightReservation().getSeats();

        PaymentMethodDto paymentMethod = flightRequestDto.getFlightReservation().getPaymentMethod();

        var totalIntereses = CalcularInteres.calculateInterest(paymentMethod, totalPrice);

        double totalFinal = totalPrice + totalIntereses;
        response.setUserName(flightRequestDto.getUserName());
        response.setTotalNeto(totalPrice);
        response.setTotalIntereses(totalIntereses);
        response.setTotalFinal(totalFinal);
        response.setFlightReservation(flightResponseDto);

        reservationFlight.save(response);

        return mapper.map(response, FlightResponseDTO.class);
    }


}

