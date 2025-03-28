package com.tdi.sensorservice.web.dto;

import com.tdi.sensorservice.web.dto.marker.OnCreate;
import com.tdi.sensorservice.web.dto.marker.OnUpdate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

import static com.tdi.sensorservice.common.util.ValidationMessage.*;

@Getter
@Setter
@Schema(description = "Датчик")
public class SensorDto {

    @Null(message = ID_MUST_BE_NULL_ON_CREATE, groups = OnCreate.class)
    @NotNull(message = ID_REQUIRED_ON_UPDATE, groups = OnUpdate.class)
    @Schema(
            description = "Идентификатор",
            example = "7f65543f-5a94-4daf-91e1-b3ef4215d817",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private UUID id;

    @NotBlank(message = NAME_FIELD_IS_EMPTY, groups = {OnCreate.class, OnUpdate.class})
    @Length(min = 3, max = 30, message = NAME_INCORRECT_LENGTH, groups = {OnCreate.class, OnUpdate.class})
    @Schema(
            description = "Наименование",
            example = "Barometer",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String name;

    @NotBlank(message = MODEL_FIELD_IS_EMPTY, groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 15, message = MODEL_INCORRECT_LENGTH, groups = {OnCreate.class, OnUpdate.class})
    @Schema(
            description = "Модель",
            example = "ac-23",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String model;

    @NotNull(message = RANGE_FIELD_IS_EMPTY, groups = {OnCreate.class, OnUpdate.class})
    @Schema(
            description = "Радиус работы",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @Valid
    private RangeDto range;

    @NotBlank(message = TYPE_FIELD_IS_EMPTY, groups = {OnCreate.class, OnUpdate.class})
    @Schema(
            description = "Тип",
            example = "Temperature",
            requiredMode = Schema.RequiredMode.REQUIRED,
            allowableValues = {"Pressure", "Voltage", "Temperature", "Humidity"}
    )
    private String type;

    @Schema(
            description = "Единица измерения",
            example = "°С",
            allowableValues = {"bar", "voltage", "°С", "%"}
    )
    private String unit;

    @Length(max = 40, message = LOCATION_INCORRECT_LENGTH, groups = {OnCreate.class, OnUpdate.class})
    @Schema(description = "Местоположение", example = "kitchen")
    private String location;

    @Length(max = 200, message = DESCRIPTION_INCORRECT_LENGTH, groups = {OnCreate.class, OnUpdate.class})
    @Schema(description = "Описание", example = "description")
    private String description;

}