package com.sprint1.AgenciaDeTurismo.Controller;

import com.sprint1.AgenciaDeTurismo.Model.Hotel;
import com.sprint1.AgenciaDeTurismo.Service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AgencyController {

    @Autowired
    AgencyService agencyService;
    @GetMapping("/")
    public List<Hotel> get(){
       return agencyService.get();
    }
}
