package com.example.webapplicationexample.controller;

import com.example.webapplicationexample.model.CroppedProduct;
import com.example.webapplicationexample.model.Product;
import com.example.webapplicationexample.repository.ProductRepository;
import com.example.webapplicationexample.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Управление запросами связанными с продуктами
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Добавляет новый продукт
     * @param product информация о продукте
     * @return id нового продукта
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        log.info("Добавление продукта {}", product);
        if(product.getName() == null || product.getPrice() == null){
            return ResponseEntity.badRequest().body("Заполните все возможные поля");
        }
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
    public List<CroppedProduct> getProducts(@RequestParam(required = false) String name) {

        log.info("Поиск продуктов по имени {}", name);
        if(name == null){
            name="";
        }
        return productService.findAll(name)
                .stream()
                .map(CroppedProduct::new)
                .toList();
    }

    /**
     * Возвращает список продуктов по id
     * @param id - индификатор продукта
     * @return продукт
     */
    @GetMapping("/{id}")
    public ResponseEntity<CroppedProduct> getProducts(@PathVariable long id) {
        log.info("Поиск продуктов по id: {}", id);
        Optional<Product> product = productService.findById(id);

        if (product.isPresent()) {
            return ResponseEntity.ok().body(new CroppedProduct(product.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Обновляет информацию о продукте
     * @param product - информация о продукте
     * @return новый продукт
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        log.info("Обновляет информацию о продукте: {}", product);
        if(product.getAmount() < 0){
            return ResponseEntity.badRequest().body("Количество товара не может быть отрицательным числом");
        }
        productService.update(product);
        return ResponseEntity.ok().body(new CroppedProduct(product));
    }

    /**
     * Удаляет продукт
     * @param id - индификатор
     * @return возвращает статус операции
     */
    @PreAuthorize("hasRole('ADMIN')")
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
