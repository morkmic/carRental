package com.example.carRental.service;


import com.example.carRental.model.User;
import com.example.carRental.model.UserRole;
import com.example.carRental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public User getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        return user;

    }

    public User addUser(User user) {
        Optional<User> userDB = userRepository.findByUsername(user.getUsername());
        if (userDB.isPresent()) {
            throw new IllegalStateException("user exists");
        }
        user.setUserRole(UserRole.USER);
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        userRepository.delete(user);

    }

}
