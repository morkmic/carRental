package com.example.carRental.service;

import com.example.carRental.dto.EmployeeDto;
import com.example.carRental.model.Employee;
import com.example.carRental.model.UserRole;
import com.example.carRental.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.NoSuchElementException;
import java.util.Optional;

public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public Employee getEmployee(Long employeeId) {
        Employee employe = employeeRepository.findById(employeeId).orElseThrow(() -> new NoSuchElementException());
        return employe;

    }

    public Employee addUser(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setUsername(employeeDto.getUsername());
        employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
        employee.setEmail(employeeDto.getEmail());
        employee.setUserRole(UserRole.EMPLOYEE);
        Optional<Employee> employeeDB = employeeRepository.findByUsername(employee.getUsername());
        if (employeeDB.isPresent()) {
            throw new IllegalStateException("employee exists");
        }

        Employee savedEmployee = employeeRepository.save(employee);
        return savedEmployee;
    }

    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new NoSuchElementException());
        employeeRepository.delete(employee);

    }
}
