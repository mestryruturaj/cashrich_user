package com.cashrich.BackendAssignment.exception;

import com.cashrich.BackendAssignment.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> methodArgumentNotValidExceptionHandler(Exception exception) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatusCode(HttpStatus.BAD_REQUEST);
        errorDto.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        errorDto.setMessage(exception.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> exceptionHandler(Exception exception) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        errorDto.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        errorDto.setMessage(exception.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
