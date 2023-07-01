package com.example.webapplicationexample.service;

import com.example.webapplicationexample.model.Product;
import com.example.webapplicationexample.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для взаимоднействия с продуктами
 */
@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public long save(Product product) {
        return productRepository.save(product).getId();
    }

    @Override
    public Optional<Product> findById(long id) {

        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAll(String name) {
        return productRepository.findByNameIsLike("%"+name+"%");
    }

    @Override
    public boolean update(Product product) {
        productRepository.save(product);
        return true;
    }

    @Override
    public boolean deleteById(long id) {
        productRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean isProductExists(long id) {
        Optional<Product> product = findById(id);
        return product.isPresent();
    }
}
