package com.springBoot.restfulApiBlogApp.utils;

import com.springBoot.restfulApiBlogApp.payload.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Payload;

@Service
public class Responder<T> {
    public  ResponseEntity<APIResponse> okay(String message, boolean result, T payLoad ){
        return new ResponseEntity<>(new APIResponse<>(message, result, payLoad), HttpStatus.OK);
    }
}
