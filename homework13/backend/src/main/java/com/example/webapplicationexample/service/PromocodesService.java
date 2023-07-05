package com.example.webapplicationexample.service;

import com.example.webapplicationexample.model.Promocode;

import java.util.Optional;

/**
 * Сервис для взаимоднействия с промокодами
 */
public interface PromocodesService {
    boolean save(Promocode promocode);
    Optional<Promocode> findPromocode(String promocode);
    boolean update(Promocode promocode);
    boolean delete(String promocode);
}
