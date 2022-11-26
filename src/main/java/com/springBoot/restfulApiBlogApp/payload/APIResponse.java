package com.springBoot.restfulApiBlogApp.payload;

import lombok.*;
import org.springframework.stereotype.Service;


@Setter
@Getter
@NoArgsConstructor
public class APIResponse<T> {
    private String message;
    private  Boolean success;
    private T payload;

    public APIResponse(String message, Boolean success, T payload) {
        this.message = message;
        this.success = success;
        this.payload = payload;
    }
}
