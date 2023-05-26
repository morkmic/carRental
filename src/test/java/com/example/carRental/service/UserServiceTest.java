package com.example.carRental.service;

import com.example.carRental.model.User;
import com.example.carRental.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest {
    private UserService userService;
    private UserRepository userRepositoryMock;
    private PasswordEncoder passwordEncoderMock;

    @BeforeEach
    public void setUp() {
        userRepositoryMock = Mockito.mock(UserRepository.class);
        passwordEncoderMock = Mockito.mock(PasswordEncoder.class);
        userService = new UserService();
    }
    @Test
    void whenGetUser_thenReturnUser() {
        Long userId = 1L;
        User user = new User();
        user.setUsername("user");
        user.setEmail("user@example.com");

        Mockito.when(userRepositoryMock.findById(userId)).thenReturn(Optional.of(user));
        User returnedUser = userService.getUser(userId);
        assertEquals(returnedUser, user);
    }

}
