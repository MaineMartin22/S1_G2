package com.sprint1.AgenciaDeTurismo.Service;

import com.sprint1.AgenciaDeTurismo.Model.Hotel;
import com.sprint1.AgenciaDeTurismo.Model.Flight;
import com.sprint1.AgenciaDeTurismo.Repository.HotelRepository;
import com.sprint1.AgenciaDeTurismo.Repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Service
public class AgencyService {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    FlightRepository flightRepository;

    public List<Hotel> get(){
        return hotelRepository.dataHotels();
    }

    public List<Hotel> getHotelDisponible(String dateFrom, String dateTo, String destination){
        return hotelRepository.getHotelDisponible(dateFrom, dateTo, destination);
    }

    public List<Flight> getFlight(){return flightRepository.dataFlights();}

}
