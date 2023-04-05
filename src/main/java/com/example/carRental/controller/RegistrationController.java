package com.example.carRental.controller;

import com.example.carRental.dto.EmployeeDto;
import com.example.carRental.dto.UserDto;
import com.example.carRental.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/register")
public class RegistrationController {
    @Autowired
     RegistrationService registrationService;

    @PostMapping
    public ResponseEntity register(@RequestBody UserDto userDto) {
        return registrationService.register(userDto);
    }

    @PostMapping("/registerEmployee")
    public ResponseEntity registerEmployee(@RequestBody EmployeeDto employeeDto) {
        return registrationService.registerEmployee(employeeDto);
    }

}
