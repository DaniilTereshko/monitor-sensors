package com.tdi.sensorservice.service.impl;

import com.tdi.sensorservice.model.Sensor;
import com.tdi.sensorservice.model.Type;
import com.tdi.sensorservice.model.Unit;
import com.tdi.sensorservice.repository.SensorRepository;
import com.tdi.sensorservice.repository.TypeRepository;
import com.tdi.sensorservice.repository.UnitRepository;
import com.tdi.sensorservice.service.SensorService;
import com.tdi.sensorservice.web.dto.SensorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.tdi.sensorservice.common.exception.ResourceNotFoundException.resourceNotFoundException;
import static com.tdi.sensorservice.common.util.ExceptionMessage.*;

@Service
@Transactional
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService {

    private final SensorRepository sensorRepository;
    private final TypeRepository typeRepository;
    private final UnitRepository unitRepository;

    @Override
    @Transactional(readOnly = true)
    public Sensor getById(final UUID id) {
        return getOrThrow(id);
    }

    @Override
    public Sensor create(SensorDto request) {
        return saveOrUpdateSensor(new Sensor(), request);
    }

    @Override
    public void delete(final UUID id) {
        var sensor = getOrThrow(id);
        sensorRepository.delete(sensor);
    }

    @Override
    public Sensor update(SensorDto request) {
        var sensor = getOrThrow(request.getId());
        return saveOrUpdateSensor(sensor, request);
    }

    private Sensor saveOrUpdateSensor(Sensor sensor, SensorDto request) {
        var type = getTypeByNameOrThrow(request.getType());
        var unit = getUnitByNameOrThrow(request.getUnit());

        sensor.setName(request.getName());
        sensor.setModel(request.getModel());
        sensor.setRange(request.getRange());
        sensor.setType(type);
        sensor.setUnit(unit);
        sensor.setLocation(request.getLocation());
        sensor.setDescription(request.getDescription());

        return sensorRepository.save(sensor);
    }

    private Sensor getOrThrow(final UUID id) {
        return sensorRepository.findById(id)
                .orElseThrow(resourceNotFoundException(RESOURCE_NOT_FOUND_BY_ID, id));
    }

    private Type getTypeByNameOrThrow(final String typeName) {
        return typeRepository.findByName(typeName)
                .orElseThrow(resourceNotFoundException(RESOURCE_NOT_FOUND_BY_ATTRIBUTE, Type.class.getSimpleName(), "name", typeName));
    }

    private Unit getUnitByNameOrThrow(final String unitName) {
        return unitRepository.findByName(unitName)
                .orElseThrow(resourceNotFoundException(RESOURCE_NOT_FOUND_BY_ATTRIBUTE, Unit.class.getSimpleName(), "name", unitName));
    }

}