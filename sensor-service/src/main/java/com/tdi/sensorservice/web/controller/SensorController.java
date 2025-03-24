package com.tdi.sensorservice.web.controller;

import com.tdi.sensorservice.service.SensorService;
import com.tdi.sensorservice.web.dto.SensorDto;
import com.tdi.sensorservice.web.dto.marker.OnCreate;
import com.tdi.sensorservice.web.dto.marker.OnUpdate;
import com.tdi.sensorservice.web.mapper.SensorMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "Датчик", description = "Контроллер для базовых операций над датчиками")
@RestController
@RequestMapping("/api/v1/sensor")
@RequiredArgsConstructor
public class SensorController {

    private final SensorService sensorService;

    private final SensorMapper sensorMapper;

    @Operation(
            summary = "Получение датчика по ID",
            description = "Позволяет получить датчик по указанному идентификатору"
    )
    @GetMapping("/{id}")
    public ResponseEntity<SensorDto> getById(
            @Parameter(description = "Идентификатор", required = true)
            @PathVariable
            final UUID id
    ) {
        var sensor = sensorService.getById(id);
        var dto = sensorMapper.toDto(sensor);
        return ResponseEntity.ok(dto);
    }

    @Operation(
            summary = "Создание датчика",
            description = "Позволяет создать датчик"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SensorDto> create(@Validated(OnCreate.class) @RequestBody SensorDto request) {
        var sensor = sensorService.create(request);
        var dto = sensorMapper.toDto(sensor);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dto);
    }

    @Operation(
            summary = "Удаление датчика",
            description = "Позволяет удалить датчик по указанному идентификатору"
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(
            @Parameter(description = "Идентификатор", required = true)
            @PathVariable
            final UUID id
    ) {
        sensorService.delete(id);
        return ResponseEntity.noContent()
                .build();
    }

    @Operation(
            summary = "Обновление датчика",
            description = """
                    Позволяет обновить датчик по указанному идентификатору. Для обновления
                    пердоставляется полный список полей объекта, включая идентификатор"""
    )
    @PutMapping
    public ResponseEntity<SensorDto> update(@Validated(OnUpdate.class) @RequestBody SensorDto request) {
        var sensor = sensorService.update(request);
        var dto = sensorMapper.toDto(sensor);
        return ResponseEntity.ok(dto);
    }

}