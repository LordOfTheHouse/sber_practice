package com.example.webapplicationexample.repository;

import com.example.webapplicationexample.model.PromoCode;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Хранит промокоды
 */
@Repository
public class LocalPromoCodesRepository implements PromoCodesRepository {
    List<PromoCode> promoCodes = List.of(
            new PromoCode("lol", 10),
            new PromoCode("1111", 15));
    @Override
    public int getDiscount(String promoCode) {
        return promoCodes.stream()
                .filter(promoCode1 -> promoCode1.getPromoCode().equals(promoCode))
                .findAny()
                .map(PromoCode::getDiscount)
                .orElse(0);
    }
}
