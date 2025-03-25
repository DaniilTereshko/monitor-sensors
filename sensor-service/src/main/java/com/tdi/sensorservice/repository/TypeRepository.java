package com.tdi.sensorservice.repository;

import com.tdi.sensorservice.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeRepository extends JpaRepository<Type, Integer> {

    Optional<Type> findByName(String name);

}