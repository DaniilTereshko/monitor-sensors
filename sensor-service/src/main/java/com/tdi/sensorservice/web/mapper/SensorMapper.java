package com.tdi.sensorservice.web.mapper;

import com.tdi.sensorservice.model.Sensor;
import com.tdi.sensorservice.web.dto.SensorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SensorMapper {

    @Mapping(target = "type", source = "type.name")
    @Mapping(target = "unit", source = "unit.name")
    SensorDto toDto(Sensor entity);

    @Mapping(target = "type", source = "type.name")
    @Mapping(target = "unit", source = "unit.name")
    List<SensorDto> toDto(List<Sensor> entity);

}