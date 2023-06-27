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
    private List<Cart> carts = new ArrayList<>();

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

    @Override
    public Cart generate(long id){
        Cart cart = new Cart(id, new ArrayList<>(), "");
        carts.add(cart);
        return cart;
    }

}
