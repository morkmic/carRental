package com.example.carRental.controller;

import com.example.carRental.model.User;
import com.example.carRental.repository.UserRepository;
import com.example.carRental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Configuration
@RestController
//@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @GetMapping("admin/users")
    public ResponseEntity getUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("admin/users/{userId}")
    public ResponseEntity getUser(@PathVariable Long userId) {
        return userService.getUser(userId);

    }

  //  @PostMapping("/users/addUser")
    public ResponseEntity addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping("admin/users/{userId}")
    public ResponseEntity deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }


}
