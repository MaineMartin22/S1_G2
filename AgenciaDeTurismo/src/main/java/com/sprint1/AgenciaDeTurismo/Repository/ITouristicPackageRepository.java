package com.sprint1.AgenciaDeTurismo.Repository;

import com.sprint1.AgenciaDeTurismo.Entity.TouristicPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITouristicPackageRepository extends JpaRepository<TouristicPackage, Integer> {
}
