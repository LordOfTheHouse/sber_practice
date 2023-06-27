package com.example.webapplicationexample.controller;

import com.example.webapplicationexample.model.Transfer;
import com.example.webapplicationexample.service.LocalPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Получения запросов связанных с оплатой
 */
@Slf4j
@RestController
@RequestMapping("payment")
public class PaymentController {
    LocalPaymentService localPaymentService;

    public PaymentController(LocalPaymentService localPaymentService) {
        this.localPaymentService = localPaymentService;
    }

    /**
     * Оплачивает покупку пользователя
     * @param transfer информация для платежа
     * @return статус оплаты
     */
    @PostMapping
    public ResponseEntity<?> pay(@RequestBody Transfer transfer) {
        log.info("Совершение платежа {}", transfer);
        boolean isPay = localPaymentService.pay(transfer);
        if (isPay) {
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
