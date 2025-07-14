package com.karolina.UniScoreCalc.Repository;

import com.karolina.UniScoreCalc.Model.SpecialtyCoefficient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecialtyCoefficientRepository extends JpaRepository<SpecialtyCoefficient, Long> {
    // findById
}

