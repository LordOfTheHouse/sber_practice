package com.example.webapplicationexample.service;

import com.example.webapplicationexample.model.Promocode;
import com.example.webapplicationexample.repository.PromoCodesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Сервис для взаимодействия с промокодами
 */
@Service
public class PomocodesServiceImpl implements PromocodesService{
    PromoCodesRepository promoCodesRepository;

    @Autowired
    public PomocodesServiceImpl(PromoCodesRepository promoCodesRepository) {
        this.promoCodesRepository = promoCodesRepository;
    }

    @Override
    public boolean save(Promocode promocode) {
        promoCodesRepository.save(promocode);
        return true;
    }

    @Override
    public Optional<Promocode> findPromocode(String promocode) {
        return promoCodesRepository.findByPromocodeIgnoreCase(promocode);
    }

    @Override
    public boolean update(Promocode promocode) {
        Optional<Promocode> promo = findPromocode(promocode.getPromocode());
        if(promo.isPresent()){
            promo.get().setPercent(promocode.getPercent());
            promoCodesRepository.save(promo.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String promocode) {
        promoCodesRepository.deleteByPromocode(promocode);
        return false;
    }
}
