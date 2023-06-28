package com.example.webapplicationexample.controller;

import com.example.webapplicationexample.exception.AccountNotDefined;
import com.example.webapplicationexample.exception.InsufficientFundsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Определение ситуации с недостатком средств
 */
@Slf4j
@RestControllerAdvice
public class InsufficientFundsHandler {

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<?> handleInsufficientExceptions(InsufficientFundsException ex) {
        log.info("Недостаточно средств");
        return  ResponseEntity.badRequest().build();
    }
    @ExceptionHandler(AccountNotDefined.class)
    public ResponseEntity<?> handleAccountNotDefinedExceptions(AccountNotDefined ex) {
        log.info("Аккаунт не определен со стороны банка");
        return  ResponseEntity.badRequest().build();

    }
}
