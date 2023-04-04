package com.example.carRental.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDto {
    private int id;
@NotEmpty
    private String username;
@NotEmpty
    private String password;
@NotEmpty
    private String email;
}
