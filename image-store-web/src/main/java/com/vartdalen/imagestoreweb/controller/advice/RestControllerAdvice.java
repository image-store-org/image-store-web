package com.vartdalen.imagestoreweb.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ValidationException;
import java.net.ConnectException;

@ControllerAdvice(annotations = {RestController.class})
public class RestControllerAdvice {

    @ExceptionHandler({ValidationException.class, MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<Exception> handleBadRequestErrors(Exception ex) {
        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<Exception> handleServiceUnavailableErrors(Exception ex) {
        return new ResponseEntity<>(ex, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
