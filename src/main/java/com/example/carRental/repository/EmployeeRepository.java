package com.example.carRental.repository;

import com.example.carRental.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUsername(String username);

    Employee findFirstByUsername(String username);
}
