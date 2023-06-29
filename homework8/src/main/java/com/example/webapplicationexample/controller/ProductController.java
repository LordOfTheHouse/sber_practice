package com.example.webapplicationexample.controller;

import com.example.webapplicationexample.model.Product;
import com.example.webapplicationexample.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
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

    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Добавляет новый продукт
     * @param product информация о продукте
     * @return id нового продукта
     */
    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        log.info("Добавление продукта {}", product);
        return ResponseEntity.created(URI.create("/products/" + productRepository.save(product))).build();
    }

    /**
     * Возвращает список продуктов по имени
     * @param name - имя продукта
     * @return список продуктов
     */
    @GetMapping
    public List<Product> getProducts(@RequestParam(required = false) String name) {
        log.info("Поиск продуктов по имени {}", name);
        return productRepository.findAll(name);
    }

    /**
     * Возвращает список продуктов по id
     * @param id - индификатор продукта
     * @return продукт
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProducts(@PathVariable long id) {
        log.info("Поиск продуктов по id: {}", id);
        Optional<Product> product = productRepository.findById(id);

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
    public Product updateProduct(@RequestBody Product product) {
        log.info("Обновляет информацию о продукте: {}", product);
        productRepository.update(product);
        return product;
    }

    /**
     * Удаляет продукт
     * @param id - индификатор
     * @return возвращает статус операции
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id) {
        log.info("Удаление продукта по id: {}", id);
        boolean isDeleted = productRepository.deleteById(id);

        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
