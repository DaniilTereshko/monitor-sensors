package com.tdi.sensorservice.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ValidationMessage {

    public static final String ID_MUST_BE_NULL_ON_CREATE = "validation.sensor.id.must_be_null_on_create";
    public static final String ID_REQUIRED_ON_UPDATE = "validation.sensor.id.required_on_update";

    /* SensorDto */
    public static final String NAME_INCORRECT_LENGTH = "validation.sensor.name.incorrect_length";
    public static final String NAME_FIELD_IS_EMPTY = "validation.sensor.name.empty";
    public static final String MODEL_INCORRECT_LENGTH = "validation.sensor.model.incorrect_length";
    public static final String MODEL_FIELD_IS_EMPTY = "validation.sensor.model.empty";
    public static final String RANGE_FIELD_IS_EMPTY = "validation.sensor.range.empty";
    public static final String TYPE_FIELD_IS_EMPTY = "validation.sensor.type.empty";
    public static final String LOCATION_INCORRECT_LENGTH = "validation.sensor.location.incorrect_length";
    public static final String DESCRIPTION_INCORRECT_LENGTH = "validation.sensor.description.incorrect_length";
    public static final String RANGE_FROM_POSITIVE = "validation.sensor.range.from.positive";
    public static final String RANGE_TO_POSITIVE = "validation.sensor.range.to.positive";
    public static final String RANGE_FROM_LESS_THAN_TO = "validation.sensor.range.from_less_than_to";
    public static final String RANGE_FROM_IS_NULL = "validation.sensor.range.from.empty";
    public static final String RANGE_TO_IS_NULL = "validation.sensor.range.to.empty";

}