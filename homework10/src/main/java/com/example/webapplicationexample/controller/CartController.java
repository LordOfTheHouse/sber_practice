package com.example.webapplicationexample.controller;

import com.example.webapplicationexample.model.Product;
import com.example.webapplicationexample.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * Управление запросами связанными с корзиной
 */
@Slf4j
@RestController
@RequestMapping("carts")
public class CartController {

    CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    /**
     * Добавляет продукт в корзину
     * @param id - индификатор корзины
     * @param product - продукт
     * @return корзину после изменений
     */
    @PostMapping("/{id}")
    public ResponseEntity<?> addProduct(@PathVariable long id, @RequestBody Product product) {
        log.info("Добавление продукта {} в корзину", product);

        if(product.getAmount() < 0){
            return ResponseEntity.badRequest().body("Количество товара не может быть отрицательным числом");
        }

        if (cartService.saveProductInCart(id, product)) {
            return ResponseEntity.created(URI.create("/cart/"+id +"/product/"+product.getId())).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Обновляет количество определенного продукта в корзине
     * @param idCart - индификатор корзины
     * @param idProduct - индификатор продукта
     * @param product - продукт
     * @return корзину после изменений
     */
    @PutMapping("/{idCart}/product/{idProduct}")
    public ResponseEntity<?> updateAmount(@PathVariable long idCart, @PathVariable long idProduct,
                                          @RequestBody Product product) {
        log.info("Изменение количества товара в корзине");
        if(product.getAmount() < 0){
            return ResponseEntity.badRequest().body("Количество товара не может быть отрицательным числом");
        }
        product.setId(idProduct);
        if (cartService.updateAmountProduct(idCart, product)) {
            return ResponseEntity.ok().body(cartService.findProductsInCart(idCart));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Удаляет продукт из корзины
     * @param idCart - индификатор корзины
     * @param productId - индификатор продукта
     * @return корзину после изменений
     */
    @DeleteMapping("/{idCart}/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable long idCart, @PathVariable long productId) {
        log.info("Удаление продукта {} из корзины", productId);
        boolean isDeleted = cartService.deleteProductInCart(idCart, productId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}