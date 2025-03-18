package org.ecommerce.storeapp.service;

import org.ecommerce.storeapp.model.Cart;
import org.ecommerce.storeapp.model.User;
import org.ecommerce.storeapp.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart getCartById(int id) {
        return cartRepository.findById(id).orElse(null);
    }

    public Cart createCart(User user, Product product, Long amount) {
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProduct(product);
        cart.setAmount(amount);
        return cartRepository.save(cart);
    }

    public Cart updateCart(int id,
            , User user, Product product, Long amount) {

        return cartRepository.findById(id).map(cart -> {
            if (user != null) {
                cart.setUser(user);
            }
            if (product != null) {
                cart.setProduct(product);
            }
            if (amount != null) {
                cart.setAmount(amount);
            }
            return cartRepository.save(cart);
        }).orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public void deleteCart(int id) {
        cartRepository.deleteById(id);
    }


}