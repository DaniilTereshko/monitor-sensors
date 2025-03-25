package com.tdi.sensorservice.service;

import com.tdi.sensorservice.model.Sensor;
import com.tdi.sensorservice.web.dto.SensorDto;

import java.util.UUID;

public interface SensorService extends CRUDService<Sensor, SensorDto, UUID> {
}