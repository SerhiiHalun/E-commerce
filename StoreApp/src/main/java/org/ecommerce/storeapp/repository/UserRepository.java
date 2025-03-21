package org.ecommerce.storeapp.repository;

import jakarta.validation.constraints.Email;
import org.ecommerce.storeapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(@Email String email);
}
