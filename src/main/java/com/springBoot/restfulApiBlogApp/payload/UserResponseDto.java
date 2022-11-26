package com.springBoot.restfulApiBlogApp.payload;

import lombok.Data;

@Data
public class UserResponseDto {
    private String username;
    private String email;
}
