package com.cashrich.BackendAssignment.service;

import com.cashrich.BackendAssignment.dto.SignUpRequest;
import com.cashrich.BackendAssignment.dto.SignUpResponse;

public interface UserService {
    public SignUpResponse signUp(SignUpRequest signUpRequest);
}
