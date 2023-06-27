package com.example.webapplicationexample.repository;

import com.example.webapplicationexample.model.Cart;
import com.example.webapplicationexample.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Осуществляет операции с корзиной
 */
@Repository
public class LocalCartRepository implements CartRepository{
    List<Cart> carts = new ArrayList<>(List.of(new Cart(1L, new ArrayList<>(), "11")));

    @Override
    public Optional<Cart> addProductInCartById(long id, Product product) {
        Optional<Cart> cart = carts.stream().filter(c -> c.getId()==id).findAny();
        if(cart.isPresent()){
            cart.get().getProducts().add(product);
        }
        return cart;
    }

    @Override
    public Optional<Cart> updateAmountProduct(long idCart, long idProduct,int amount) {
        Optional<Cart> cart = carts.stream().filter(c -> c.getId()==idCart).findAny();
        if(cart.isPresent()){
            Optional<Product> product = cart.get()
                    .getProducts()
                    .stream()
                    .filter(p->p.getId()==idProduct)
                    .findAny();
            if(product.isPresent()){
                product.get().setAmount(amount);
            }
        }
        return cart;
    }

    @Override
    public boolean deleteProduct(long idCart, long idProduct) {
        Optional<Cart> cart = carts.stream().filter(c -> c.getId()==idCart).findAny();
        if(cart.isPresent()){
            return cart.get().getProducts().removeIf(product -> product.getId() == idProduct);
        }
        return false;
    }

    public Cart generate(){
        Cart cart = new Cart(generateId(), new ArrayList<>(), "");
        carts.add(cart);
        return cart;
    }

    private long generateId() {
        Random random = new Random();
        int low = 1;
        int high = 1_000_000;
        return random.nextLong(high - low) + low;
    }
}
