package com.sprint1.AgenciaDeTurismo.unit.service;

import com.sprint1.AgenciaDeTurismo.DTO.HotelDTO;
import com.sprint1.AgenciaDeTurismo.DTO.TouristicPackageDTO;
import com.sprint1.AgenciaDeTurismo.Entity.Hotel;
import com.sprint1.AgenciaDeTurismo.Entity.TouristicPackage;
import com.sprint1.AgenciaDeTurismo.Repository.ITouristicPackageRepository;
import com.sprint1.AgenciaDeTurismo.Service.TouristicPackageService;
import com.sprint1.AgenciaDeTurismo.utils.Hotel.HotelDTOFactory;
import com.sprint1.AgenciaDeTurismo.utils.Hotel.HotelFactory;
import com.sprint1.AgenciaDeTurismo.utils.TouristicPackage.TouristicDTOFactory;
import com.sprint1.AgenciaDeTurismo.utils.TouristicPackage.TouristicFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;


@ExtendWith(MockitoExtension.class)

class TouristicPackageServiceTest {

    @Mock
    ITouristicPackageRepository touristicPackageRepository;
    @InjectMocks
    TouristicPackageService touristicPackageService;

    @Test
    void getAllEntities() {
        // arrange
        List<TouristicPackage> expected = List.of(TouristicFactory.getPaquete1(),
                TouristicFactory.getPaquete2());

        List<TouristicPackageDTO> expectedDTO = List.of(TouristicDTOFactory.getPaquete1DTO(),
                TouristicDTOFactory.getPaquete2DTO());
        // act
        Mockito.when(touristicPackageRepository.findAll()).thenReturn(expected);
        var result = touristicPackageService.getAllEntities();

        // assert
        Assertions.assertEquals(expectedDTO, result);
    }
}