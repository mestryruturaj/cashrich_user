package com.cashrich.BackendAssignment.controller;

import com.cashrich.BackendAssignment.dto.SignUpRequest;
import com.cashrich.BackendAssignment.dto.SignUpResponse;
import com.cashrich.BackendAssignment.service.UserService;
import com.cashrich.BackendAssignment.validator.SignUpValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private SignUpValidator signUpValidator;
    private UserService userService;

    @Autowired
    public UserController(SignUpValidator signUpValidator, UserService userService) {
        this.signUpValidator = signUpValidator;
        this.userService = userService;
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest signUpRequest) {
        //TODO Exception handling
        /*Validate before processing*/
        signUpValidator.validateSignUpRequest(signUpRequest);

        /*Perform business logic*/
        SignUpResponse signUpResponse = userService.signUp(signUpRequest);

        /*Return response*/
        return new ResponseEntity<>(signUpResponse, HttpStatus.OK);
    }
}
