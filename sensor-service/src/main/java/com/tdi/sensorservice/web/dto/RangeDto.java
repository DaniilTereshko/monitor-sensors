package com.tdi.sensorservice.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import static com.tdi.sensorservice.common.util.ValidationMessage.*;

@Getter
@Setter
@Schema(description = "Диапазон датчика")
public class RangeDto {

    @Min(value = 0, message = RANGE_FROM_POSITIVE)
    @Schema(description = "Начальное значение диапазона (должно быть положительным)")
    private int from;

    @Positive(message = RANGE_TO_POSITIVE)
    @Schema(description = "Конечное значение диапазона (должно быть больше 0)")
    private int to;

    @AssertTrue(message = RANGE_FROM_LESS_THAN_TO)
    public boolean isValidRange() {
        return from < to;
    }

}