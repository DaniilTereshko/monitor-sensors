package com.tdi.sensorservice.web.controller;

import com.tdi.sensorservice.model.Sensor;
import com.tdi.sensorservice.service.SensorService;
import com.tdi.sensorservice.web.dto.SensorDto;
import com.tdi.sensorservice.web.dto.marker.OnCreate;
import com.tdi.sensorservice.web.dto.marker.OnUpdate;
import com.tdi.sensorservice.web.mapper.SensorMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Tag(name = "Датчик", description = "Контроллер для базовых операций над датчиками")
@Validated
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
    @SecurityRequirement(name = "JWT")
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
            summary = "Поиск датчиков",
            description = "Позволяет получить список датчиков путем полнотекстового поиска по полям name и model. " +
                    "Поддерживает только английский язык"
    )
    @GetMapping("/search")
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<List<SensorDto>> search(
            @RequestParam(required = false) String query
    ) {
        var searchResult = sensorService.search(query);
        var dtos = sensorMapper.toDto(searchResult);
        return ResponseEntity.ok(dtos);
    }

    @Operation(
            summary = "Создание датчика",
            description = "Позволяет создать датчик. При создании id передается как null"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<SensorDto> create(@RequestBody @Validated(OnCreate.class) final SensorDto request) {
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
    @SecurityRequirement(name = "JWT")
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
    @SecurityRequirement(name = "JWT")
    public ResponseEntity<SensorDto> update(@RequestBody @Validated(OnUpdate.class) final SensorDto request) {
        var sensor = sensorService.update(request);
        var dto = sensorMapper.toDto(sensor);
        return ResponseEntity.ok(dto);
    }

}