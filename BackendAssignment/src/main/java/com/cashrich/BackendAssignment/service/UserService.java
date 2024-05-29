package com.cashrich.BackendAssignment.service;

import com.cashrich.BackendAssignment.dto.*;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {
    public SignUpResponse signUp(SignUpRequest signUpRequest);

    public LoginResponse login(LoginRequest loginRequest);

    public UpdateResponse update(UpdateRequest updateRequest);

    public String logout();

    public ResponseEntity<String> getCoins(Map<String, String> headers);
}
