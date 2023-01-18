package com.leasing.administrator.leasingadministratorbackend.controllers;

import com.leasing.administrator.leasingadministratorbackend.dtos.Value;
import com.leasing.administrator.leasingadministratorbackend.exceptions.InvalidDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(InvalidDataException exception) {
        return new ResponseEntity<>(new Value<>(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
