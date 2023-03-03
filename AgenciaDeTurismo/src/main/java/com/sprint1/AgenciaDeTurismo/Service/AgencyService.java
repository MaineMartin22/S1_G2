package com.sprint1.AgenciaDeTurismo.Service;

import com.sprint1.AgenciaDeTurismo.Model.Hotel;
import com.sprint1.AgenciaDeTurismo.Repository.HotelRepository;
import com.sprint1.AgenciaDeTurismo.Repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AgencyService {
    @Autowired
    HotelRepository hotelRepository;
    FlightRepository flightRepository;

    public List<Hotel> get(){
        return hotelRepository.dataHotels();
    }
}
