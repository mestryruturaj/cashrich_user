package com.cashrich.BackendAssignment.validator;

import com.cashrich.BackendAssignment.Entity.User;
import com.cashrich.BackendAssignment.dto.SignUpRequest;
import com.cashrich.BackendAssignment.exception.InvalidUserDetailsException;
import com.cashrich.BackendAssignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SignUpValidator {

    private UserRepository userRepository;

    @Autowired
    public SignUpValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateSignUpRequest(SignUpRequest signUpRequest) {
        Optional<User> userByUsername = userRepository.findByUsername(signUpRequest.getUsername());
        if (userByUsername.isPresent()) {
            throw new InvalidUserDetailsException(String.format("Desired username is not available and User profile with the username '%s' already exists.", signUpRequest.getUsername()));
        }
        Optional<User> userByEmail = userRepository.findByEmail(signUpRequest.getEmail());
        if (userByEmail.isPresent()) {
            throw new InvalidUserDetailsException(String.format("Entered email is not available and User profile with the email '%s' already exists.", signUpRequest.getEmail()));
        }
    }
}
