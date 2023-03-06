package com.sprint1.AgenciaDeTurismo.Service;

import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Flight.FlightRequestDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.Hotel.*;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Flight.FlightResponse;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Flight.FlightResponseDto;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Flight.StatusFlight;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Hotel.BookingResponse;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Hotel.BookingResponseDto;
import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Hotel.StatusCodeDto;
import com.sprint1.AgenciaDeTurismo.Model.HotelModel;
import com.sprint1.AgenciaDeTurismo.Model.FlightModel;
import com.sprint1.AgenciaDeTurismo.Repository.HotelRepository;
import com.sprint1.AgenciaDeTurismo.Repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
public class AgencyService {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    FlightRepository flightRepository;

    public List<HotelModel> findAll() {
        return hotelRepository.dataHotels();
    }

    public List<HotelModel> getHotelDisponibles(String dateFrom, String dateTo, String destination) {
        return hotelRepository.getHotelDisponible(dateFrom, dateTo, destination);
    }

    public List<FlightModel> getFlight() {
        return flightRepository.dataFlights();
    }


    public BookingResponse reserva (@RequestBody BookingRequestDto bookingRequestDto){
        BookingResponse response = new BookingResponse();
        BookingResponseDto bookingResponse = new BookingResponseDto();

        HotelModel bookHotel = hotelRepository.findHotelWhitCode(bookingRequestDto.getBooking().getHotelCode());

        bookingResponse.setDateFrom(bookingRequestDto.getBooking().getDateFrom());
        bookingResponse.setDateTo(bookingRequestDto.getBooking().getDateTo());
        bookingResponse.setDestination(bookingRequestDto.getBooking().getDestination());
        bookingResponse.setHotelCode(bookingRequestDto.getBooking().getHotelCode());
        bookingResponse.setPeopleAmount(bookingRequestDto.getBooking().getPeopleAmount());
        bookingResponse.setRoomType(bookingRequestDto.getBooking().getRoomType());
        bookingResponse.setPeople(bookingRequestDto.getBooking().getPeople());

        LocalDate fechaComoLocalDateFrom = LocalDate.parse(bookingRequestDto.getBooking().getDateFrom());
        LocalDate fechaComoLocalDateTo = LocalDate.parse(bookingRequestDto.getBooking().getDateTo());

        Integer totalPrice = Math.toIntExact(ChronoUnit.DAYS.between(fechaComoLocalDateFrom, fechaComoLocalDateTo) * bookHotel.getPriceForNight());

        response.setUserName(bookingRequestDto.getUserName());
        response.setTotal(totalPrice);
        response.setBooking(bookingResponse);
        response.setStatusCode(new StatusCodeDto(200, "Proceso termino satisfactoriamente"));

        return response;

    }

    public List<FlightModel> getFlightAvailability(@RequestParam String dateFrom, @RequestParam String dateTo, @RequestParam String origin, @RequestParam String destination) {
        return flightRepository.getFlightAvailability(dateFrom, dateTo, origin, destination);
    }
    public FlightResponse flightResponse(@RequestBody FlightRequestDto flightRequestDto){
        FlightResponse response = new FlightResponse();
        FlightResponseDto flightResponseDto = new FlightResponseDto();

        FlightModel reservationFlight = flightRepository.findflight(flightRequestDto.getFlightDto().getNumberFlight());

        flightResponseDto.setNumberFlight(flightRequestDto.getFlightDto().getNumberFlight());
        flightResponseDto.setOrigin(flightRequestDto.getFlightDto().getOrigin());
        flightResponseDto.setDestiny(flightRequestDto.getFlightDto().getDestiny());
        flightResponseDto.setSeatType(flightRequestDto.getFlightDto().getSeatType());
        flightResponseDto.setPriceForPerson(flightRequestDto.getFlightDto().getPriceForPerson());
        flightResponseDto.setDeparturaDate(flightRequestDto.getFlightDto().getDeparturaDate());
        flightResponseDto.setReturnDate(flightRequestDto.getFlightDto().getReturnDate());
        flightResponseDto.setSeats(flightRequestDto.getFlightDto().getSeats());


        Integer totalPrice = (int) (reservationFlight.getPriceForPerson()*flightRequestDto.getFlightDto().getSeats());

        response.setUserName(flightRequestDto.getUserName());
        response.setTotalFlight(totalPrice);
        response.setReservation(flightResponseDto);
        response.setStatusFlight(new StatusFlight(200, "Proceso termino satisfactoriamente"));

        return response;


    }
}

