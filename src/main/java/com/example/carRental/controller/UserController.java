package com.example.carRental.controller;

import com.example.carRental.model.User;
import com.example.carRental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity getUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }
 /*  @GetMapping
   public List getUsers(){
       return userRepository.findAll();
   }*/

    @PostMapping
    public ResponseEntity addUser(@RequestBody User user) {
        Optional<User> userDB = userRepository.findByUsername(user.getUsername());
        if(userDB.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

}
