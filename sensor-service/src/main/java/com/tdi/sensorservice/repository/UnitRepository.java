package com.tdi.sensorservice.repository;

import com.tdi.sensorservice.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<Unit, Integer> {
}