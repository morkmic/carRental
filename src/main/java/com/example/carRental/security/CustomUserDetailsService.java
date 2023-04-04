package com.example.carRental.security;

import com.example.carRental.model.User;
import com.example.carRental.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findFirstByUsername(username);

        if(user != null){
            Stream roleStream =  Stream.of(user.getUserRole());
            List roleList = (List) roleStream.map((role) ->
                    new SimpleGrantedAuthority(user.getUserRole().name())).collect(Collectors.toList());
            org.springframework.security.core.userdetails.User authUser =
                    new org.springframework.security.core.userdetails.User(
                            user.getPassword(),
                            user.getEmail(),
                            roleList
                        );
            return authUser;
        }else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }
}
