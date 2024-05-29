package com.cashrich.BackendAssignment.service;

import com.cashrich.BackendAssignment.dto.*;

public interface UserService {
    public SignUpResponse signUp(SignUpRequest signUpRequest);

    public LoginResponse login(LoginRequest loginRequest);

    public UpdateResponse update(UpdateRequest updateRequest);

    public String logout();
}
