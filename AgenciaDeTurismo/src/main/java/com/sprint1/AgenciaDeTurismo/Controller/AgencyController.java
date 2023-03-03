package com.sprint1.AgenciaDeTurismo.Controller;

import com.sprint1.AgenciaDeTurismo.Model.Hotel;
import com.sprint1.AgenciaDeTurismo.Service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
public class AgencyController {

    @Autowired
    AgencyService agencyService;
    @GetMapping("/api/v1/hotels")
    public List<Hotel> get(){
       return agencyService.get();
    }

    @GetMapping("/api/v1/hotel")
    // /api/v1/hotels?dateFrom=dd/mm/aaaa&dateTo=dd/mm/aaaa&destination=Puerto Iguazu
    public List<Hotel> hotelesDisponibles(@RequestParam String dateFrom, @RequestParam String dateTo, @RequestParam String destination){
        return agencyService.getHotelDisponible(dateFrom, dateTo, destination);
    }
}
