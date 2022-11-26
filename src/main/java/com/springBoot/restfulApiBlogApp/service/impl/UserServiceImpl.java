package com.springBoot.restfulApiBlogApp.service.impl;

import com.springBoot.restfulApiBlogApp.config.security.JwtService;
import com.springBoot.restfulApiBlogApp.entity.User;
import com.springBoot.restfulApiBlogApp.enums.Role;
import com.springBoot.restfulApiBlogApp.payload.LoginDto;
import com.springBoot.restfulApiBlogApp.payload.UserRequestDto;
import com.springBoot.restfulApiBlogApp.payload.UserResponseDto;
import com.springBoot.restfulApiBlogApp.repository.UserRepository;
import com.springBoot.restfulApiBlogApp.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Object create(UserRequestDto requestDto, Role role) {
        Optional<User> userExist =userRepository.findByEmail(requestDto.getEmail());
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        if (userExist.isPresent()){
            throw new RuntimeException();
        }
        User user = new User();
        String userName = requestDto.getFirstName() + requestDto.getLastName();
        user.setUsername(userName);
        user.setEmail(requestDto.getEmail());
        user.setPassword(encodedPassword);
        user.setRole(role);
        userRepository.save(user);

        if (user.getRole() == Role.CUSTOMER){
            String userName1 = requestDto.getFirstName() + requestDto.getLastName();
            User user1 = new User();
            user1.setUsername(userName1);
            user1.setEmail(requestDto.getEmail());
            user1.setPassword(encodedPassword);
            userRepository.save(user1);
            UserResponseDto responseDto = new UserResponseDto();
            responseDto.setUsername(user1.getUsername());
            responseDto.setEmail(user1.getEmail());
            return responseDto;

        }
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setUsername(user.getUsername());
        responseDto.setEmail(user.getEmail());
        return responseDto;
    }

    @Override
    public String login(LoginDto dto) throws AuthenticationException {
        Authentication auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));

        if(auth.isAuthenticated()){
            String jwt = "Bearer: " + JwtService.generateToken
                    (new org.springframework.security.core.userdetails.User(dto.getEmail(), dto.getPassword(), new ArrayList<>()));
            return jwt;
        } else {
            throw new AuthenticationException("Email or Password Not Authenticated");
        }
    }
}
