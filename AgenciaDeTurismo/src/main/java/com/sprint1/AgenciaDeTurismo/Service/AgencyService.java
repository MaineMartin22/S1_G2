package com.sprint1.AgenciaDeTurismo.Service;

import com.sprint1.AgenciaDeTurismo.DTO.HotelDTO;
import com.sprint1.AgenciaDeTurismo.Model.Hotel;
import com.sprint1.AgenciaDeTurismo.Model.Flight;
import com.sprint1.AgenciaDeTurismo.Repository.HotelRepository;
import com.sprint1.AgenciaDeTurismo.Repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Service
public class AgencyService {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    FlightRepository flightRepository;

    public List<Hotel> findAll(){
        return hotelRepository.dataHotels();
    }

    public List<Hotel> getHotelDisponibles(String dateFrom, String dateTo, String destination){
        return hotelRepository.getHotelDisponible(dateFrom, dateTo, destination);
    }

    public List<Flight> getFlight(){return flightRepository.dataFlights();}

    public List<Flight> getFlightAvailability(@RequestParam String dateFrom, @RequestParam String dateTo, @RequestParam String origin, @RequestParam String destination) {
        return flightRepository.getFlightAvailability(dateFrom,dateTo,origin,destination);
    }

}
