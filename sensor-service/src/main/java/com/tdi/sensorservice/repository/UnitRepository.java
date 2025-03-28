package com.tdi.sensorservice.repository;

import com.tdi.sensorservice.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnitRepository extends JpaRepository<Unit, Integer> {

    Optional<Unit> findByName(String name);

}