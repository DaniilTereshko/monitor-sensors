package com.tdi.sensorservice.model;

import com.tdi.sensorservice.model.converter.ObjectConverter;
import com.tdi.sensorservice.web.dto.RangeDto;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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
    @JdbcTypeCode(SqlTypes.JSON)
    @Convert(converter = ObjectConverter.class)
    private RangeDto range;

    @Column(nullable = false)
    @OneToOne
    private Type type;

    @OneToOne
    private Unit unit;

    private String location;

    private String description;

}