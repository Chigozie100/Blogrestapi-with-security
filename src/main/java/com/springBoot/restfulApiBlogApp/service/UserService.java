package com.springBoot.restfulApiBlogApp.service;

import com.springBoot.restfulApiBlogApp.enums.Role;
import com.springBoot.restfulApiBlogApp.payload.LoginDto;
import com.springBoot.restfulApiBlogApp.payload.UserRequestDto;
import com.springBoot.restfulApiBlogApp.payload.UserResponseDto;
import org.apache.tomcat.websocket.AuthenticationException;

public interface UserService {
Object create(UserRequestDto requestDto, Role role);
String login(LoginDto dto) throws AuthenticationException;
}
