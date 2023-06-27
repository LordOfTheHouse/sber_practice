package com.example.webapplicationexample.controller;

import com.example.webapplicationexample.model.Cart;
import com.example.webapplicationexample.model.Product;
import com.example.webapplicationexample.repository.CartRepository;
import com.example.webapplicationexample.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Управление запросами связанными с корзиной
 */
@Slf4j
@RestController
@RequestMapping("carts")
public class CartController {
    CartRepository cartRepository;
    ProductRepository productRepository;

    public CartController(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    /**
     * Добавляет продукт в корзину
     * @param id - индификатор корзины
     * @param product - продукт
     * @return корзину после изменений
     */
    @PostMapping("/{id}")
    public ResponseEntity<Cart> addProduct(@PathVariable long id, @RequestBody Product product) {
        log.info("Добавление продукта {} в корзину", product);
        Optional<Product> p = productRepository.findById(product.getId());
        if (p.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        p.get().setAmount(product.getAmount());
        Optional<Cart> cart = cartRepository.addProductInCartById(id, p.get());
        if (cart.isPresent()) {
            return ResponseEntity.ok().body(cart.get());
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
    public ResponseEntity<Cart> updateAmount(@PathVariable long idCart, @PathVariable long idProduct,
                                             @RequestBody Product product) {
        log.info("Изменение количества товара в корзине");
        Optional<Cart> cart = cartRepository.updateAmountProduct(idCart,idProduct, product.getAmount());
        if (cart.isPresent()) {
            return ResponseEntity.ok().body(cart.get());
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
        boolean isDeleted = cartRepository.deleteProduct(idCart, productId);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
