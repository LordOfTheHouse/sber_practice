package com.example.webapplicationexample.repository;

/**
 * Хранит информацию о промокодах
 */
public interface PromoCodesRepository {
    /**
     * Возвращает скидку по промокоду
     * @param promoCode - промокод
     * @return скидку
     */
    int getDiscount(String promoCode);
}
