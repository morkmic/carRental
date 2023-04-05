package com.example.carRental.service;


import com.example.carRental.dto.UserDto;
import com.example.carRental.model.User;
import com.example.carRental.model.UserRole;
import com.example.carRental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public User getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        return user;

    }

    public User addUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setUserRole(UserRole.USER);
        Optional<User> userDB = userRepository.findByUsername(user.getUsername());
        if (userDB.isPresent()) {
            throw new IllegalStateException("user exists");
        }
        User savedUser = userRepository.save(user);
        return savedUser;
    }
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        userRepository.delete(user);

    }

}
