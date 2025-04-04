package org.ecommerce.storeapp.repository;

import org.ecommerce.storeapp.model.Address;
import org.ecommerce.storeapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    Optional<Address> findByDefaultAddressIsTrue();
    Optional<Address> findTopByOrderByIdDesc();

    List<Address> findAllByUser(User user);
}
