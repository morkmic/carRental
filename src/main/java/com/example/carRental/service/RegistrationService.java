package com.example.carRental.service;

import com.example.carRental.dto.UserDto;
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

    public ResponseEntity register(UserDto userDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(userDto));



    }




}
