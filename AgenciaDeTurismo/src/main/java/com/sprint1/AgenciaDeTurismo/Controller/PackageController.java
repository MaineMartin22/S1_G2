package com.sprint1.AgenciaDeTurismo.Controller;

import com.sprint1.AgenciaDeTurismo.DTO.ErrorDTO;
import com.sprint1.AgenciaDeTurismo.DTO.PackageDTO;
import com.sprint1.AgenciaDeTurismo.Service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PackageController {
    @Autowired
    PackageService packageService;

    // Create
    @PostMapping("/api/v1/package/new")
    public PackageDTO createPackage(@RequestBody PackageDTO packageDTO) {
        return packageService.saveEntity(packageDTO);
    }

    // read
    @GetMapping("/api/v1/package/")
    public List<PackageDTO> packageEnDB() {
        return packageService.getAllEntities();
    }


    // update
    @PutMapping("/api/v1/package/edit")
    public PackageDTO updatePackage(@RequestParam String codePackage, @RequestBody PackageDTO packageDTO) {
        return packageService.updateEntity(packageDTO, codePackage);
    }

    // delete
    @DeleteMapping("/api/v1/package/delete")
    public ErrorDTO deleteByCode(@RequestParam String codePackage){
        return packageService.deleteEntity(codePackage);
    }
}
