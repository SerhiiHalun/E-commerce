package org.ecommerce.storeapp.service;


import jakarta.persistence.EntityNotFoundException;
import org.ecommerce.storeapp.model.Address;
import org.ecommerce.storeapp.model.User;
import org.ecommerce.storeapp.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddressById(int id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found"));
    }

    public Address createAddress(User user, String country, String city, String state,
                                 String street, String phoneNumber, String postCode, boolean isDefault) {
        Address address = new Address();
        address.setUser(user);
        address.setCountry(country);
        address.setCity(city);
        address.setState(state);
        address.setStreet(street);
        address.setPhoneNumber(phoneNumber);
        address.setPostCode(postCode);
        address.setDefault(isDefault);
        return addressRepository.save(address);
    }

    public Address updateAddress(int id, User user, String country, String city, String state,
                                 String street, String phoneNumber, String postCode, boolean isDefault) {

        return addressRepository.findById(id).map(address -> {
            if (user != null) {
                address.setUser(user);
            }
            if (country != null && !country.isBlank()) {
                address.setCountry(country);
            }
            if (city != null && !city.isBlank()) {
                address.setCity(city);
            }
            if (state != null && !state.isBlank()) {
                address.setState(state);
            }
            if (street != null && !street.isBlank()) {
                address.setStreet(street);
            }
            if (phoneNumber != null && !phoneNumber.isBlank()) {
                address.setPhoneNumber(phoneNumber);
            }
            if (postCode != null && !postCode.isBlank()) {
                address.setPostCode(postCode);
            }
            address.setDefault(isDefault);


            return addressRepository.save(address);
        }).orElseThrow(() -> new RuntimeException("Address not found"));
    }


    public void deleteAddress(int id) {
        addressRepository.deleteById(id);
    }
}