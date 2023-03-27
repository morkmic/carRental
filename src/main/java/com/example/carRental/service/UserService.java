package com.example.carRental.service;

import com.example.carRental.model.Car;
import com.example.carRental.model.User;
import com.example.carRental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.NoSuchElementException;
import java.util.Optional;
@Service
public class UserService {
   @Autowired
    UserRepository userRepository;

    public ResponseEntity addUser(User user) {
        Optional<User> userDB = userRepository.findByUsername(user.getUsername());
        if(userDB.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

}
