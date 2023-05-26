package com.example.carRental.service;

import com.example.carRental.model.User;
import com.example.carRental.model.UserRole;
import com.example.carRental.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.carRental.dto.UserDto;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserServiceTest {
    private UserService userService;
    private UserRepository userRepositoryMock;
    private PasswordEncoder passwordEncoderMock;
    private User user;
    private Long userId;

    @BeforeEach
    public void setUp() {
        userRepositoryMock = Mockito.mock(UserRepository.class);
        passwordEncoderMock = Mockito.mock(PasswordEncoder.class);
        userService = new UserService(userRepositoryMock, passwordEncoderMock);
        userId = 1L;
        user = new User();
        user.setUsername("user");
        user.setEmail("user@example.com");
        user.setPassword("password");
    }

    @Test
    void shouldBeAbleToGetUserByIdAndThenReturnIt() {
        //given

        //when
        Mockito.when(userRepositoryMock.findById(userId)).thenReturn(Optional.of(user));
        User returnedUser = userService.getUser(userId);
        //then
        assertEquals(returnedUser, user);
    }

    @Test
    void nonExistingUserShouldThrowExceptionWhenFindUserById() {
        //given
        Long userId = 1L;
        User user = new User();

        //when
        //then
        assertThrows(NoSuchElementException.class, () -> userService.getUser(2L));
    }

    @Test
    void shouldSaveGivenUser() {
        UserDto userDto = new UserDto();
        userDto.setUsername("user");
        userDto.setPassword("password");
        userDto.setEmail("user@example.com");

        User savedUser = new User();
        savedUser.setUsername(userDto.getUsername());
        savedUser.setPassword("encodedPassword");
        savedUser.setEmail(userDto.getEmail());
        savedUser.setUserRole(UserRole.USER);

        Mockito.when(passwordEncoderMock.encode(userDto.getPassword())).thenReturn("encodedPassword");
        Mockito.when(userRepositoryMock.findByUsername(userDto.getUsername())).thenReturn(Optional.empty());
        Mockito.when(userRepositoryMock.save(Mockito.any(User.class))).thenReturn(savedUser);

        User returnedUser = userService.addUser(userDto);
        assertEquals(returnedUser, savedUser);
    }

    @Test
    void alreadyExistUsershouldThrowException() {
        UserDto userDto = new UserDto();
        userDto.setUsername("user");
        userDto.setPassword("password");
        userDto.setEmail("user@example.com");

        Optional<User> user = Optional.of(this.user);

        Mockito.when(userRepositoryMock.findByUsername(userDto.getUsername())).thenReturn(user);

        assertThrows(IllegalStateException.class, () -> {
            userService.addUser(userDto);
        });
    }

    @Test
    void shouldDeleteUserWithGivenId() {

        Mockito.when(userRepositoryMock.findById(userId)).thenReturn(Optional.of(user));
        userService.deleteUser(userId);
        Mockito.verify(userRepositoryMock, Mockito.times(1)).delete(user);
    }

    @Test
    void notExistingUserShouldThrowExceptionDuringDelete() {


        Mockito.when(userRepositoryMock.findById(userId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            userService.deleteUser(userId);
        });
    }
}