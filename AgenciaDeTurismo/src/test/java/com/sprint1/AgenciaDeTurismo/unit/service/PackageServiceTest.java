package com.sprint1.AgenciaDeTurismo.unit.service;

import com.sprint1.AgenciaDeTurismo.DTO.ErrorDTO;
import com.sprint1.AgenciaDeTurismo.DTO.PackageDTO;
import com.sprint1.AgenciaDeTurismo.Entity.Package;
import com.sprint1.AgenciaDeTurismo.Repository.IPackageRepository;
import com.sprint1.AgenciaDeTurismo.Service.PackageService;
import com.sprint1.AgenciaDeTurismo.utils.Data.ErrorDTOFactory;
import com.sprint1.AgenciaDeTurismo.utils.Package.PackageDTOFactory;
import com.sprint1.AgenciaDeTurismo.utils.Package.PackageFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PackageServiceTest {

    @Mock
    IPackageRepository packageRepository;

    @InjectMocks
    PackageService packageService;

    @Test
    void saveEntity() {
    }

    @Test
    void getAllEntities() {
        // arrange
        List<Package> expected = List.of(PackageFactory.getPackage1(),
                PackageFactory.getPackage2());

        List<PackageDTO> expectedDTO = List.of(PackageDTOFactory.getPackage1(),
                PackageDTOFactory.getPackage2());
        // act
        Mockito.when(packageRepository.findAll()).thenReturn(expected);
        var result = packageService.getAllEntities();

        // assert
        Assertions.assertEquals(expectedDTO, result);
    }

    @Test
    void updateEntity() {
        // arrange
        PackageDTO expectedDTO = PackageDTOFactory.getPackage1();

        Package expected = PackageFactory.getPackage1();

        String code = "PACK-001";

        // act
        Mockito.when(packageRepository.findPackageByCodePackage(code)).thenReturn(Optional.of(expected));
        var result = packageService.updateEntity(expectedDTO, code);

        // assert
        Assertions.assertEquals(expectedDTO, result);
    }

    @Test
    void getEntityByCode() {
        // arrange
        PackageDTO expectedDTO = PackageDTOFactory.getPackage1();

        Package expected = PackageFactory.getPackage1();

        String codePackage = "PACK-001";

        // act
        Mockito.when(packageRepository.findPackageByCodePackage(codePackage)).thenReturn(Optional.of(expected));
        var result = packageService.getEntityByCode(codePackage);

        // assert
        Assertions.assertEquals(expectedDTO, result);
    }

    @Test
    void deleteEntity() {
        // arrange
        ErrorDTO expectedDTO = ErrorDTOFactory.deletePack1();

        Package expected = PackageFactory.getPackage1();

        String codePackage = "PACK-001";

        // act
        Mockito.when(packageRepository.findPackageByCodePackage(codePackage)).thenReturn(Optional.of(expected));
        var result = packageService.deleteEntity(codePackage);

        // assert
        Assertions.assertEquals(expectedDTO, result);
    }

    @Test
    void deleteReservaEntity() {
    }
}