package com.springBoot.restfulApiBlogApp.controller;

import com.springBoot.restfulApiBlogApp.enums.Role;
import com.springBoot.restfulApiBlogApp.payload.APIResponse;
import com.springBoot.restfulApiBlogApp.payload.LoginDto;
import com.springBoot.restfulApiBlogApp.payload.UserRequestDto;
import com.springBoot.restfulApiBlogApp.service.UserService;
import com.springBoot.restfulApiBlogApp.utils.Responder;
import lombok.AllArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class UserController {
    private final UserService service;
    private final Responder responder;

    @PostMapping("/createUser")
    public ResponseEntity<Object> createUser(@RequestBody UserRequestDto requestDto){
        return new ResponseEntity<>(service.create(requestDto, Role.ADMIN), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse> login(@RequestBody LoginDto dto) throws AuthenticationException {
        String response = service.login(dto);
        return responder.okay("success",true,response);
    }


}
