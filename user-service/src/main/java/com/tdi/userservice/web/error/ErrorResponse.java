package com.tdi.userservice.web.error;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Ошибка")
public record ErrorResponse(String timestamp, String errorCode, String message) {

    public ErrorResponse(String errorCode, String message) {
        this(LocalDateTime.now().toString(), errorCode, message);
    }

    public static ErrorResponse of(String errorCode, String message) {
        return new ErrorResponse(errorCode, message);
    }

}