package com.example.carRental.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Entity
@Data
@NoArgsConstructor

@Table(name = "employees", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class Employee extends User{
    private String firstname;
    private String lastname;

    public Employee(String username, String password, String email, UserRole userRole,
                    String firstname, String lastname) {

        super(username, password, email, userRole);
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
