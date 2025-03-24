package com.tdi.sensorservice.repository;

import com.tdi.sensorservice.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Integer> {
}