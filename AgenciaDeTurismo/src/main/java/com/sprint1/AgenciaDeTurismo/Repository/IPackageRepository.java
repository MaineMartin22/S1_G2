package com.sprint1.AgenciaDeTurismo.Repository;


import com.sprint1.AgenciaDeTurismo.Entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPackageRepository extends JpaRepository<Package, Integer> {

    Optional<Package> findPackageByCodePackage(String codePackage);
}
