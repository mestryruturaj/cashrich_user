package com.cashrich.BackendAssignment.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

@Data
public class ErrorDto {
    private HttpStatus statusCode;
    private String message;
    private Timestamp timestamp;
}
