package com.sprint1.AgenciaDeTurismo.Service;

import com.sprint1.AgenciaDeTurismo.DTO.ErrorDTO;

import com.sprint1.AgenciaDeTurismo.DTO.TouristicPackageDTO;

import com.sprint1.AgenciaDeTurismo.Entity.TouristicPackage;
import com.sprint1.AgenciaDeTurismo.Repository.IFlightRepository;
import com.sprint1.AgenciaDeTurismo.Repository.IHotelRepository;
import com.sprint1.AgenciaDeTurismo.Repository.ITouristicPackageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TouristicPackageService implements ITouristicPackageService{

    @Autowired
    ITouristicPackageRepository touristicPackageRepository;

    @Autowired
    IHotelRepository hotelRepository;

    @Autowired
    IFlightRepository flightRepository;


    ModelMapper mapper = new ModelMapper();
    @Override
    public TouristicPackageDTO saveEntity(TouristicPackageDTO objectDTO) {
        // mappear de dto a entity para llevar al repo
        var entity = mapper.map(objectDTO, TouristicPackage.class);

            entity.setHotels(
                    entity.getHotels().stream().map(hotel -> hotelRepository.findById(hotel.getId()).get()).collect(Collectors.toList())
            );


            entity.setFlights(
                    entity.getFlights().stream().map(flight -> flightRepository.findById(flight.getId()).get()).collect(Collectors.toList())
            );

        // guardar
        touristicPackageRepository.save(entity);

        // mappear de entity a dto para llevar al controller

        return mapper.map(entity, TouristicPackageDTO.class);
    }

    @Override
    public List<TouristicPackageDTO> getAllEntities() {
        // buscar todos los resultados en el repo
        var list = touristicPackageRepository.findAll();
        // luego convertir de entidad a DTO
        return list.stream().map(
                        touristicPackage -> mapper.map(touristicPackage, TouristicPackageDTO.class)
                )
                .collect(Collectors.toList());
    }

    @Override
    public TouristicPackageDTO updateEntity(TouristicPackageDTO objectDTO, String code) {
        return null;
    }

    @Override
    public TouristicPackageDTO getEntityByCode(String code) {
        return null;
    }

    @Override
    public ErrorDTO deleteEntity(String code) {
        return null;
    }

    @Override
    public ErrorDTO deleteReservaEntity(Integer ID) {
        return null;
    }

}
