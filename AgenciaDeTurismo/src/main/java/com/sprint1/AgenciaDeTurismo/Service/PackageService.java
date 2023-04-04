package com.sprint1.AgenciaDeTurismo.Service;

import com.sprint1.AgenciaDeTurismo.DTO.ErrorDTO;
import com.sprint1.AgenciaDeTurismo.DTO.FlightDto;
import com.sprint1.AgenciaDeTurismo.DTO.PackageDTO;

import com.sprint1.AgenciaDeTurismo.Entity.Flight;
import com.sprint1.AgenciaDeTurismo.Entity.Package;
import com.sprint1.AgenciaDeTurismo.Exception.NotFoundException;
import com.sprint1.AgenciaDeTurismo.Repository.IFlightRepository;
import com.sprint1.AgenciaDeTurismo.Repository.IHotelRepository;
import com.sprint1.AgenciaDeTurismo.Repository.IPackageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PackageService implements IPackageService{
    @Autowired
    IPackageRepository packageRepository;

    @Autowired
    IHotelRepository hotelRepository;

    @Autowired
    IFlightRepository flightRepository;
    ModelMapper mapper = new ModelMapper();
    @Override
    public PackageDTO saveEntity(PackageDTO objectDTO) {
        var entity = mapper.map(objectDTO, Package.class);

        if(objectDTO.getFlight().getId() != null && objectDTO.getHotel().getId() != null) {
            entity.setFlight(
                    flightRepository.findById(objectDTO.getFlight().getId()).get()
            );
            entity.setHotel(
                    hotelRepository.findById(objectDTO.getHotel().getId()).get()
            );
        }
        packageRepository.save(entity);

        return mapper.map(entity, PackageDTO.class);
    }

    @Override
    public List<PackageDTO> getAllEntities() {
        var list = packageRepository.findAll();

        return list.stream().map(
                        pack -> mapper.map(pack, PackageDTO.class)
                )
                .collect(Collectors.toList());
    }

    @Override
    public PackageDTO updateEntity(PackageDTO objectDTO, String codePackage) {
        var pack = getEntityByCode(codePackage);
        if (pack.getId().equals(objectDTO.getId())) {
            var entity = mapper.map(objectDTO, Package.class);
            packageRepository.save(entity);
            return mapper.map(entity, PackageDTO.class);
        } else
            throw new NotFoundException("No existe vuelo con ese ID");
    }

    @Override
    public PackageDTO getEntityByCode(String codePackage) {
        Package pack = packageRepository.findPackageByCodePackage(codePackage)
                .orElseThrow(() -> {
                    throw new NotFoundException("No se encuentra paquete con ese código");
                });
        // mapeo de entidad a dto.
        return mapper.map(pack, PackageDTO.class);
    }

    @Override
    public ErrorDTO deleteEntity(String codePackage) {
        Package pack = packageRepository.findPackageByCodePackage(codePackage)
                .orElseThrow(() -> {
                    throw new NotFoundException("No se encuentra paquete con ese código");
                });

        packageRepository.delete(pack);

        return ErrorDTO.builder()
                .explanation("ELIMINACIÓN")
                .messages(List.of("Se elimino el paquete con código " + codePackage))
                .build();
    }

    @Override
    public ErrorDTO deleteReservaEntity(Integer id) {
        return null;
    }
}
