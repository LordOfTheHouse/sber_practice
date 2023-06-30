package com.example.webapplicationexample.controller;

import com.example.webapplicationexample.model.Transfer;
import com.example.webapplicationexample.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Получения запросов связанных с оплатой
 */
@Slf4j
@RestController
@RequestMapping("payment")
public class PaymentController {
    PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Оплачивает покупку пользователя
     * @param transfer информация для платежа
     * @return статус оплаты
     */
    @PostMapping
    public BigDecimal pay(@RequestBody Transfer transfer) {
        log.info("Совершение платежа {}", transfer);
        return paymentService.pay(transfer);
    }
}
