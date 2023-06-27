package com.example.webapplicationexample.controller;

import com.example.webapplicationexample.exception.InsufficientFundsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Опредление ситуации с недостатком средств
 */
@Slf4j
@RestControllerAdvice
public class InsufficientFundsHandler {

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<?> handleAllExceptions(InsufficientFundsException ex) {
        log.info("Недостаточно средств");
        return  ResponseEntity.badRequest().build();

    }
}
