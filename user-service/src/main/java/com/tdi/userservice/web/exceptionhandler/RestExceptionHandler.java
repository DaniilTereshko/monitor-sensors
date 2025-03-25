package com.tdi.userservice.web.exceptionhandler;

import com.tdi.userservice.common.exception.ResourceAlreadyExistsException;
import com.tdi.userservice.common.exception.ResourceNotFoundException;
import com.tdi.userservice.common.exception.UnauthorizedAccessException;
import com.tdi.userservice.common.util.ExceptionMessage;
import com.tdi.userservice.web.error.ErrorCode;
import com.tdi.userservice.web.error.ErrorResponse;
import com.tdi.userservice.web.error.FieldErrorResponse;
import com.tdi.userservice.web.error.ValidationErrorResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex){
        var message = getLocalizedMessage(ex.getLocalizedMessage(), ex.getArgs());
        var response = ErrorResponse.of(ErrorCode.USER_ERROR.name(), message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex){
        var message = getLocalizedMessage(ex.getLocalizedMessage(), ex.getArgs());
        var response = ErrorResponse.of(ErrorCode.USER_ERROR.name(), message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedAccessException(UnauthorizedAccessException ex){
        var message = getLocalizedMessage(ex.getLocalizedMessage(), ex.getArgs());
        var response = ErrorResponse.of(ErrorCode.USER_ERROR.name(), message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErrorResponse> handleBadRequest() {
        var message = getLocalizedMessage(ExceptionMessage.REQUEST_NOT_READABLE);
        var response = ErrorResponse.of(ErrorCode.USER_ERROR.name(), message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RuntimeException.class, Error.class, Exception.class})
    public ResponseEntity<ErrorResponse> handleInnerError(Exception ex) {
        ex.printStackTrace(); // для отладки
        var message = getLocalizedMessage(ExceptionMessage.SERVER_INTERNAL_ERROR);
        var response = ErrorResponse.of(ErrorCode.SERVER_ERROR.name(), message);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getLocalizedMessage(String key, Object... args) {
        return messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }

}