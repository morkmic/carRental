package com.example.carRental.controller;

import com.example.carRental.dto.UserDto;
import com.example.carRental.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class RegistrationController {
    @Autowired
     RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserDto userDto) {
        return registrationService.register(userDto);
    }



}
