package com.example.carRental.controller;

import com.example.carRental.model.User;
import com.example.carRental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity getUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users.toString());
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

}
