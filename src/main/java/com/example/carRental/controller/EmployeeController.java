package com.example.carRental.controller;

import com.example.carRental.model.Employee;
import com.example.carRental.repository.EmployeeRepository;
import com.example.carRental.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Configuration
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public ResponseEntity getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity getEmployee(@PathVariable Long employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);
        return ResponseEntity.ok(employee);

    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity deleteEmployee(@PathVariable Long employeeId) {
         employeeService.deleteEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }


}
