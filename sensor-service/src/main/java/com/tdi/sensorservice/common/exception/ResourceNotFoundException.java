package com.tdi.sensorservice.common.exception;

import java.text.MessageFormat;
import java.util.function.Supplier;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Object... args) {
        super(MessageFormat.format(message, args));
    }

    public static Supplier<ResourceNotFoundException> resourceNotFoundException(String message) {
        return () -> new ResourceNotFoundException(message);
    }

    public static Supplier<ResourceNotFoundException> resourceNotFoundException(String message, Object... args) {
        return () -> new ResourceNotFoundException(message, args);
    }

}