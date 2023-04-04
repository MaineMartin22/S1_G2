package com.sprint1.AgenciaDeTurismo.Controller;


import com.sprint1.AgenciaDeTurismo.DTO.ResponseDto.Hotel.BookingResponseDTO;
import com.sprint1.AgenciaDeTurismo.Service.TouristicPackageService;
import com.sprint1.AgenciaDeTurismo.DTO.TouristicPackageDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class TouristicPackageController {

    @Autowired
    TouristicPackageService touristicPackageService;

    //Alta de un nuevo paquete
    @PostMapping("/api/v1/touristic-packages")
    public TouristicPackageDTO Create(@RequestBody TouristicPackageDTO touristicPackageDTO) {
        return touristicPackageService.saveEntity(touristicPackageDTO);
    }
    @GetMapping("/api/v1/touristic-packages")
    public List<TouristicPackageDTO> PacketesEnLaDB() {
        return touristicPackageService.getAllEntities();
    }

}
