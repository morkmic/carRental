package com.example.carRental.model;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee extends User{
    private String firstname;
    private String lastname;


}
