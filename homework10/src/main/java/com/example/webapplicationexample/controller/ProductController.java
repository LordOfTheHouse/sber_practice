package com.example.webapplicationexample.controller;

import com.example.webapplicationexample.model.Product;
import com.example.webapplicationexample.repository.ProductRepository;
import com.example.webapplicationexample.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Управление запросами связанными с продуктами
 */
@Slf4j
@RestController
@RequestMapping("products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Добавляет новый продукт
     * @param product информация о продукте
     * @return id нового продукта
     */
    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        log.info("Добавление продукта {}", product);
        if(product.getAmount() < 0){
            return ResponseEntity.badRequest().body("Количество товара не может быть отрицательным числом");
        }
        return ResponseEntity.created(URI.create("/products/" + productService.save(product))).build();
    }

    /**
     * Возвращает список продуктов по имени
     * @param name - имя продукта
     * @return список продуктов
     */
    @GetMapping
    public List<Product> getProducts(@RequestParam(required = false) String name) {
        log.info("Поиск продуктов по имени {}", name);
        return productService.findAll(name);
    }

    /**
     * Возвращает список продуктов по id
     * @param id - индификатор продукта
     * @return продукт
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProducts(@PathVariable long id) {
        log.info("Поиск продуктов по id: {}", id);
        Optional<Product> product = productService.findById(id);

        if (product.isPresent()) {
            return ResponseEntity.ok().body(product.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Обновляет информацию о продукте
     * @param product - информация о продукте
     * @return новый продукт
     */
    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        log.info("Обновляет информацию о продукте: {}", product);
        if(product.getAmount() < 0){
            return ResponseEntity.badRequest().body("Количество товара не может быть отрицательным числом");
        }
        productService.update(product);
        return ResponseEntity.ok().body(product);
    }

    /**
     * Удаляет продукт
     * @param id - индификатор
     * @return возвращает статус операции
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id) {
        log.info("Удаление продукта по id: {}", id);
        boolean isDeleted = productService.deleteById(id);

        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
