package com.tdi.sensorservice.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tdi.sensorservice.web.dto.marker.OnCreate;
import com.tdi.sensorservice.web.dto.marker.OnUpdate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import static com.tdi.sensorservice.common.util.ValidationMessage.*;

@Getter
@Setter
@Schema(description = "Диапазон датчика")
public class RangeDto {

    @NotNull(message = RANGE_FROM_IS_NULL, groups = {OnCreate.class, OnUpdate.class})
    @Min(value = 0, message = RANGE_FROM_POSITIVE, groups = {OnCreate.class, OnUpdate.class})
    @Schema(description = "Начальное значение диапазона (должно быть положительным)")
    private Integer from;

    @NotNull(message = RANGE_TO_IS_NULL, groups = {OnCreate.class, OnUpdate.class})
    @Positive(message = RANGE_TO_POSITIVE, groups = {OnCreate.class, OnUpdate.class})
    @Schema(description = "Конечное значение диапазона (должно быть больше 0)")
    private Integer to;

    @Schema(hidden = true)
    @JsonIgnore
    private boolean validRange;

    @AssertTrue(message = RANGE_FROM_LESS_THAN_TO, groups = {OnCreate.class, OnUpdate.class})
    public boolean isValidRange() {
        if (from == null || to == null) return false;
        return from < to;
    }

}