package com.cashrich.BackendAssignment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class SignUpResponse {
    private HttpStatus httpStatus;
    private String message;
    private UserDto userDto;
}
