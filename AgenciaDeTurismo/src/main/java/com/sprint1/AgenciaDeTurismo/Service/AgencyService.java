package com.sprint1.AgenciaDeTurismo.Service;

import com.sprint1.AgenciaDeTurismo.DTO.HotelDTO;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.BookingDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PaymentMethodDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.PeopleDto;
import com.sprint1.AgenciaDeTurismo.DTO.RequestDto.ReqReservaHotelDto;
import com.sprint1.AgenciaDeTurismo.Model.Hotel;
import com.sprint1.AgenciaDeTurismo.Model.Flight;
import com.sprint1.AgenciaDeTurismo.Repository.HotelRepository;
import com.sprint1.AgenciaDeTurismo.Repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Service
public class AgencyService {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    FlightRepository flightRepository;

    public List<Hotel> findAll() {
        return hotelRepository.dataHotels();
    }

    public List<Hotel> getHotelDisponibles(String dateFrom, String dateTo, String destination) {
        return hotelRepository.getHotelDisponible(dateFrom, dateTo, destination);
    }

    public List<Flight> getFlight() {
        return flightRepository.dataFlights();
    }

        // no anda
        public ReqReservaHotelDto reserva (@RequestBody ReqReservaHotelDto reqReservaHotelDto){
            BookingDto bookingDto = new BookingDto();
        /*bookingDto.setDateFrom(reqReservaHotelDto.getBookingDto().getDateFrom());
        bookingDto.setDateTo(reqReservaHotelDto.getBookingDto().getDateTo());*/
            bookingDto.setDestination(reqReservaHotelDto.getBookingDto().getDestination());
            bookingDto.setHotelCode(reqReservaHotelDto.getBookingDto().getHotelCode());
            bookingDto.setPeopleAmount(reqReservaHotelDto.getBookingDto().getPeopleAmount());
            bookingDto.setRoomType(reqReservaHotelDto.getBookingDto().getRoomType());
            bookingDto.setPeople(reqReservaHotelDto.getBookingDto().getPeople());


            ReqReservaHotelDto reqReservaHotelDto1 = new ReqReservaHotelDto(bookingDto);
            System.out.println(reqReservaHotelDto1);

            return reqReservaHotelDto1;

        }

    public List<Flight> getFlightAvailability(@RequestParam String dateFrom, @RequestParam String dateTo, @RequestParam String origin, @RequestParam String destination) {
        return flightRepository.getFlightAvailability(dateFrom, dateTo, origin, destination);
    }
}

