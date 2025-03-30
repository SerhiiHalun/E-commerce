package org.ecommerce.storeapp.repository;

import org.ecommerce.storeapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByDiscount(int discount);
    List<Product> findByDiscountGreaterThan(int discount);

    List<Product> findByCategoryId(int categoryId);
}
