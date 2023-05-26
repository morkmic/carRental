package com.example.carRental.service;

import com.example.carRental.dto.EmployeeDto;
import com.example.carRental.model.Employee;
import com.example.carRental.model.UserRole;
import com.example.carRental.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public Employee getEmployee(Long employeeId) {
        Employee employe = employeeRepository.findById(employeeId).orElseThrow(() -> new NoSuchElementException());
        return employe;

    }

    public Employee addEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(
                employeeDto.getUsername(),
                passwordEncoder.encode(employeeDto.getPassword()),
                employeeDto.getEmail(),
                UserRole.EMPLOYEE,
                employeeDto.getFirstname(),
                employeeDto.getLastname());

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
