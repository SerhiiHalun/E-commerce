package org.ecommerce.storeapp.service;

import org.ecommerce.storeapp.model.Role;
import org.ecommerce.storeapp.model.User;
import org.ecommerce.storeapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }


    public User createUser(String firstName, String lastName, String email, String password) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password); //TODO password encoding
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    public User updateUser(int id,
                           String firstName, String lastName, String email, String password, String role) {

        return userRepository.findById(id).map(user -> {
            if (firstName != null && !firstName.isBlank()) {
                user.setFirstName(firstName);
            }
            if (lastName != null && !lastName.isBlank()) {
                user.setLastName(lastName);
            }
            if (email != null && !email.isBlank()) {
                user.setEmail(email);
            }
            if (password != null && !password.isBlank()) {
                user.setPassword(password); // TODO password encoding
            }
            if (role != null) {
                try {
                    user.setRole(Role.valueOf(role.toUpperCase()));
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Invalid role: " + role);
                }
            }
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }


    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
