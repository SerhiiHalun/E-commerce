package org.ecommerce.storeapp.service;

import jakarta.persistence.EntityNotFoundException;
import org.ecommerce.storeapp.model.User;
import org.ecommerce.storeapp.model.Wishlist;
import org.ecommerce.storeapp.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public List<Wishlist> getAllWishlists() {
        return wishlistRepository.findAll();
    }

    public Wishlist getWishlistById(int id) {
        return wishlistRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Wishlist not found"));
    }

    public Wishlist createWishlist(User user, Product product) {
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlist.setProduct(product);

        return wishlistRepository.save(wishlist);
    }
    public Wishlist updateWishlist(int id, User user, Product product) {
        Wishlist wishlist = getWishlistById(id);
        wishlist.setUser(user);
        wishlist.setProduct(product);
        return wishlistRepository.save(wishlist);
    }

    public void deleteWishlist(int id) {
        wishlistRepository.deleteById(id);
    }
}
