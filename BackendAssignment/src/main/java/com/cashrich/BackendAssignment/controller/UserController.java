package com.cashrich.BackendAssignment.controller;

import com.cashrich.BackendAssignment.dto.*;
import com.cashrich.BackendAssignment.exception.InvalidUserDetailsException;
import com.cashrich.BackendAssignment.service.UserService;
import com.cashrich.BackendAssignment.validator.SignUpValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.cashrich.BackendAssignment.constants.Constants.USER_CREATION_FAILED;

@RestController
@RequestMapping(value = "user")
public class UserController {
    private SignUpValidator signUpValidator;
    private UserService userService;

    @Autowired
    public UserController(SignUpValidator signUpValidator, UserService userService) {
        this.signUpValidator = signUpValidator;
        this.userService = userService;
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<SignUpResponse> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        /*Validate before processing*/
        try {
            signUpValidator.validateSignUpRequest(signUpRequest);
        } catch (InvalidUserDetailsException exception) {
            throw exception;
        }

        /*Perform business logic*/
        SignUpResponse signUpResponse = userService.signUp(signUpRequest);

        /*Return response*/
        return new ResponseEntity<>(signUpResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = userService.login(loginRequest);
        return new ResponseEntity<>(loginResponse, loginResponse.getHttpStatus());
    }

    @PutMapping(value = "/update")
    public ResponseEntity<UpdateResponse> update(@Valid @RequestBody UpdateRequest updateRequest) {
        UpdateResponse updateResponse = userService.update(updateRequest);
        return new ResponseEntity<>(updateResponse, updateResponse.getHttpStatus());
    }

    @GetMapping(value = "/logout")
    public ResponseEntity<String> logout() {
        String response = userService.logout();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/coins")
    public ResponseEntity<String> getCoins(@Valid @RequestHeader Map<String, String> headers) {
        ResponseEntity<String> response = userService.getCoins(headers);
        return response;
    }

}
