package com.tdi.sensorservice.web.exceptionhandler;

import com.tdi.sensorservice.common.exception.ResourceNotFoundException;
import com.tdi.sensorservice.common.util.ExceptionMessage;
import com.tdi.sensorservice.web.error.ErrorCode;
import com.tdi.sensorservice.web.error.ErrorResponse;
import com.tdi.sensorservice.web.error.ValidationErrorResponse;
import com.tdi.sensorservice.web.error.FieldErrorResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Locale;

@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex, Locale locale) {
        var fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new FieldErrorResponse(
                        error.getField(),
                        messageSource.getMessage(error.getDefaultMessage(), null, locale)
                ))
                .toList();

        var errorResponse = ValidationErrorResponse.of(ErrorCode.VALIDATION_ERROR.name(), fieldErrors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationErrorResponse> handleConstraintViolationException(ConstraintViolationException ex, Locale locale) {
        var fieldErrors = ex.getConstraintViolations().stream()
                .map(violation -> new FieldErrorResponse(
                        violation.getPropertyPath().toString(),
                        messageSource.getMessage(violation.getMessage(), null, locale)
                ))
                .toList();

        var errorResponse = ValidationErrorResponse.of(ErrorCode.VALIDATION_ERROR.name(), fieldErrors);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(ResourceNotFoundException ex, Locale locale){
        var message = messageSource.getMessage(ex.getMessage(), null, locale);
        var response = new ErrorResponse(ErrorCode.USER_ERROR.name(), message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErrorResponse> handleBadRequest(Locale locale) {
        var message = messageSource.getMessage(ExceptionMessage.REQUEST_NOT_READABLE, null, locale);
        var response = new ErrorResponse(ErrorCode.USER_ERROR.name(), message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RuntimeException.class, Error.class, Exception.class})
    public ResponseEntity<ErrorResponse> handleInnerError(Exception ex, Locale locale) {
        ex.printStackTrace(); // для отладки
        var message = messageSource.getMessage(ExceptionMessage.SERVER_INTERNAL_ERROR, null, locale);
        var response = new ErrorResponse(ErrorCode.SERVER_ERROR.name(), message);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}