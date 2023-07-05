package com.example.webapplicationexample.controller;

import com.example.webapplicationexample.model.Customer;
import com.example.webapplicationexample.model.Product;
import com.example.webapplicationexample.service.CartService;
import com.example.webapplicationexample.service.ClientService;
import com.example.webapplicationexample.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

/**
 * Управление запросами связанными с корзиной
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("carts")
public class CartController {

    CartService cartService;
    ClientService clientService;
    ProductService productService;

    @Autowired
    public CartController(CartService cartService, ClientService clientService, ProductService productService) {
        this.cartService = cartService;
        this.clientService = clientService;
        this.productService = productService;
    }

    /**
     * Добавляет продукт в корзину
     * @param idUser - индификатор корзины
     * @param product - продукт
     * @return корзину после изменений
     */
    @PostMapping("/{idUser}")
    public ResponseEntity<?> addProduct(@PathVariable long idUser, @RequestBody Product product) {
        log.info("Добавление продукта {} в корзину", product);

        if(product.getAmount() < 0){
            return ResponseEntity.badRequest().body("Количество товара не может быть отрицательным числом");
        }
        if(!productService.existsById(product.getId())){
            return ResponseEntity.badRequest().body("Продукта с данным id не существует");
        }
        if(!clientService.existsById(idUser)){
            return ResponseEntity.badRequest().body("Клиента с данным id не существует");
        }
        if (cartService.saveProductInCart(idUser, product)) {
            return ResponseEntity.created(URI.create("/cart/"+idUser +"/product/"+product.getId())).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Обновляет количество определенного продукта в корзине
     * @param idUser - индификатор корзины
     * @param idProduct - индификатор продукта
     * @param product - продукт
     * @return корзину после изменений
     */
    @PutMapping("/{idUser}/product/{idProduct}")
    public ResponseEntity<?> updateAmount(@PathVariable long idUser, @PathVariable long idProduct,
                                          @RequestBody Product product) {
        log.info("Изменение количества товара в корзине");
        if(product.getAmount() < 0){
            return ResponseEntity.badRequest().body("Количество товара не может быть отрицательным числом");
        }
        if(!productService.existsById(idProduct)){
            return ResponseEntity.badRequest().body("Продукта с данным id не существует");
        }
        if(!clientService.existsById(idUser)){
            return ResponseEntity.badRequest().body("Клиента с данным id не существует");
        }
        product.setId(idProduct);
        if (cartService.updateAmountProduct(idUser, product)) {
            return ResponseEntity.ok().body(cartService.findProductsInCart(idUser));
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
        Optional<Customer> customer = clientService.findById(idCart);
        Optional<Product> product = productService.findById(productId);
        if(customer.isEmpty() || product.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        boolean isDeleted = cartService.deleteProductInCart(idCart, productId);

        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}