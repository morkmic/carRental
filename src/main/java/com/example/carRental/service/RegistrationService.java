package com.example.carRental.service;

import com.example.carRental.model.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class RegistrationService {
@Autowired
    UserService userService;

    public ResponseEntity register(User user) {

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(user));



    }




}
