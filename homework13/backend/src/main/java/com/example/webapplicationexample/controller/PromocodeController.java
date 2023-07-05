package com.example.webapplicationexample.controller;

import com.example.webapplicationexample.model.Promocode;
import com.example.webapplicationexample.service.PromocodesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

/**
 * Получения запросов связанных с промокодами
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("promocode")
public class PromocodeController {
    private PromocodesService promocodesService;

    @Autowired
    public PromocodeController(PromocodesService promocodesService) {
        this.promocodesService = promocodesService;
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Promocode promocode) {
        log.info("Добавление промокода {}", promocode);
        if(promocode.getPromocode() == null){
            return ResponseEntity.badRequest().body("Заполните графу промокод");
        }
        if(promocode.getPercent()<0 || promocode.getPercent()>100){
            return ResponseEntity.badRequest().body("проценты должны быть от 0 до 100");
        }
        promocodesService.save(promocode);
        return ResponseEntity.created(URI.create("/" + promocode.getPromocode())).build();

    }

    @GetMapping("/{promo}")
    public ResponseEntity<Promocode> getCustomer(@PathVariable String promo) {
        log.info("Поиск промокода {}", promo);
        Optional<Promocode> promocode = promocodesService.findPromocode(promo);

        if (promocode.isPresent()) {
            return ResponseEntity.ok().body(promocode.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{promo}")
    public ResponseEntity<?> updateAmount(@PathVariable String promo,
                                          @RequestBody Promocode promocode) {
        log.info("Изменение промокода");
        if(promocode.getPercent()<0 || promocode.getPercent()>100){
            return ResponseEntity.badRequest().body("проценты долны быть от 0 до 100");
        }
        promocode.setPromocode(promo);
        if (promocodesService.update(promocode)) {
            return ResponseEntity.ok().body(promocodesService.findPromocode(promo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{promo}")
    public ResponseEntity<?> deleteProduct(@PathVariable String promo) {
        log.info("Удаление промокода {} из корзины", promo);
        boolean isDeleted = promocodesService.delete(promo);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
