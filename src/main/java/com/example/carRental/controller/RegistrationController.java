package com.example.carRental.controller;

import com.example.carRental.dto.RegistrationDto;
import com.example.carRental.model.User;
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

    @PostMapping("/registration")
    public ResponseEntity register(@RequestBody RegistrationDto registrationDto) {
        return registrationService.register(registrationDto);
    }



}
