package com.springBoot.restfulApiBlogApp.payload;

import com.springBoot.restfulApiBlogApp.enums.Role;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
@Data
public class UserRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
