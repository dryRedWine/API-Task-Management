package com.internship.apitaskmanagement.error;


import com.internship.apitaskmanagement.error.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {


    @ExceptionHandler({RangeTimeException.class, NullPointerException.class, CustomValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationException(final RuntimeException e) {
        log.error("Ошибка валидации (400) - {}", e.getMessage(), e);
        return ApiError.builder()
                .errors(e.getStackTrace())
                .message(e.getMessage())
                .reason("The request was incorrect.")
                .status(HttpStatus.BAD_REQUEST.name())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler({UserAccessException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiError handleUnauthorizedException(final RuntimeException e) {
        log.warn("Нет прав доступа к запрашиваемому ресурсу (401) - {} ", e.getMessage(), e);
        return ApiError.builder()
                .errors(e.getStackTrace())
                .message(e.getMessage())
                .reason("The request lacks valid authentication credentials for the target resource.")
                .status(HttpStatus.UNAUTHORIZED.name())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler({UserNotFoundException.class, TaskNotFoundException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleNotFoundException(final RuntimeException e) {
        log.warn("Объект не найден (404) - {}", e.getMessage(), e);
        return ApiError.builder()
                .message(e.getMessage())
                .reason("The required object was not found.")
                .status(HttpStatus.NOT_FOUND.name())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleConflictException(final RuntimeException e) {
        log.error("Конфликт данных (409) - {}", e.getMessage(), e);
        return ApiError.builder()
                .errors(e.getStackTrace())
                .message(e.getCause().getMessage())
                .reason("Integrity constraint has been violated")
                .status(HttpStatus.CONFLICT.name())
                .timestamp(LocalDateTime.now())
                .build();
    }

}