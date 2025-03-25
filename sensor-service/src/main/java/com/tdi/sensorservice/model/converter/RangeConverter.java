package com.tdi.sensorservice.model.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdi.sensorservice.web.dto.RangeDto;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.SneakyThrows;

@Converter
public class RangeConverter implements AttributeConverter<RangeDto, String> {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @SneakyThrows
    public String convertToDatabaseColumn(RangeDto attribute) {
        return objectMapper.writeValueAsString(attribute);
    }

    @Override
    @SneakyThrows
    public RangeDto convertToEntityAttribute(String dbData) {
        return objectMapper.readValue(dbData, RangeDto.class);
    }
}