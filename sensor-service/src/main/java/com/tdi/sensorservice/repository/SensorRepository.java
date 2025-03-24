package com.tdi.sensorservice.repository;

import com.tdi.sensorservice.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SensorRepository extends JpaRepository<Sensor, UUID> {
}