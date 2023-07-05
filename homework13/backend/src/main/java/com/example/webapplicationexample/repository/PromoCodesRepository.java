package com.example.webapplicationexample.repository;


import com.example.webapplicationexample.model.Promocode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *  Осуществляет операции с промокодами
 */
@Repository
public interface PromoCodesRepository extends JpaRepository<Promocode, Long> {

    Optional<Promocode> findByPromocodeIgnoreCase(String promocode);


    void deleteByPromocode(String promocode);
}
