package com.tdi.sensorservice.service.impl;

import com.tdi.sensorservice.model.Sensor;
import com.tdi.sensorservice.service.SensorService;
import com.tdi.sensorservice.web.dto.SensorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService {

    @Override
    @Transactional(readOnly = true)
    public Sensor getById(UUID uuid) {
        return null;
    }

    @Override
    public Sensor create(SensorDto request) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {

    }

    @Override
    public Sensor update(SensorDto request) {
        return null;
    }

}