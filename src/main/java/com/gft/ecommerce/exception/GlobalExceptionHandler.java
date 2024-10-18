package com.gft.ecommerce.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(NotFoundException notFoundException) {
        log.error("NotFoundException Thrown ", notFoundException);
        return logAndCreateErrorResponse(notFoundException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            IllegalArgumentException.class,
            MethodArgumentTypeMismatchException.class,
            MissingServletRequestParameterException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequestExceptions(Exception exception) {
        log.error("{} Thrown ", exception.getClass().getSimpleName(), exception);
        return logAndCreateErrorResponse(exception, HttpStatus.BAD_REQUEST);
    }

    private ErrorResponse logAndCreateErrorResponse(Exception exception, HttpStatus status) {
        log.error("{} Thrown ", exception.getClass().getSimpleName(), exception);
        return new ErrorResponse(
                exception.getMessage(),
                status.getReasonPhrase(),
                LocalDateTime.now().toString()
        );
    }
}