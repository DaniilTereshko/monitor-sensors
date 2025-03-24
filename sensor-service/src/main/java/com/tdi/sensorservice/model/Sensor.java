package com.tdi.sensorservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Sensor {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private Map<String, Integer> range;

    @Column(nullable = false)
    @OneToOne
    private Type type;

    @OneToOne
    private Unit unit;

    private String location;

    private String description;

}