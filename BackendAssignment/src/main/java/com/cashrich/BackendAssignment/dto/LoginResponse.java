package com.cashrich.BackendAssignment.dto;


import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class LoginResponse {
    private HttpStatus httpStatus;
    private String message;
    private String username;
    private UserDto userDetails;
}
