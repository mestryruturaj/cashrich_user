package com.cashrich.BackendAssignment.service;

import com.cashrich.BackendAssignment.Entity.User;
import com.cashrich.BackendAssignment.Entity.UserActivity;
import com.cashrich.BackendAssignment.dto.*;
import com.cashrich.BackendAssignment.externalClient.CryptoAPI;
import com.cashrich.BackendAssignment.repository.UserActivityRepository;
import com.cashrich.BackendAssignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import static com.cashrich.BackendAssignment.constants.Constants.*;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserActivityRepository userActivityRepository;
    private CryptoAPI cryptoAPI;


    private User cachedUser;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CryptoAPI cryptoAPI, UserActivityRepository userActivityRepository) {
        this.userRepository = userRepository;
        this.cryptoAPI = cryptoAPI;
        this.userActivityRepository = userActivityRepository;
    }

    @Override
    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        /*Creating and populating record*/
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(signUpRequest.getPassword());
        user.setEmail(signUpRequest.getEmail());
        user.setMobile(signUpRequest.getMobile());
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());

        /*Persisting record*/
        userRepository.save(user);

        /*Return response*/
        UserDto userDto = UserDto.createFrom(user);
        SignUpResponse signUpResponse = new SignUpResponse(HttpStatus.OK, USER_CREATION_SUCCESS, userDto);
        return signUpResponse;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        LoginResponse loginResponse = new LoginResponse();

        if (cachedUser != null) {
            loginResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            loginResponse.setUsername(loginRequest.getUsername());
            loginResponse.setMessage(USER_LOGIN_FAILED_ANOTHER_USER_SESSION_UNDERWAY + cachedUser.getUsername());
            loginResponse.setUserDetails(null);
            return loginResponse;
        }

        Optional<User> userOptional = userRepository.findByUsername(loginRequest.getUsername());
        if (userOptional.isEmpty() || !userOptional.get().getPassword().equals(loginRequest.getPassword())) {
            loginResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            loginResponse.setUsername(loginRequest.getUsername());
            loginResponse.setMessage(USER_LOGIN_FAILED_INVALID_DETAILS);
            loginResponse.setUserDetails(null);
            return loginResponse;
        }

        cachedUser = userOptional.get();

        loginResponse.setHttpStatus(HttpStatus.OK);
        loginResponse.setMessage(USER_LOGIN_SUCCESSFUL + loginRequest.getUsername());
        loginResponse.setUsername(loginRequest.getUsername());
        loginResponse.setUserDetails(UserDto.createFrom(cachedUser));
        return loginResponse;
    }

    @Override
    public UpdateResponse update(UpdateRequest updateRequest) {
        UpdateResponse updateResponse = new UpdateResponse();
        if (cachedUser == null) {
            updateResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            updateResponse.setUsername(null);
            updateResponse.setMessage(USER_UPDATE_FAILED_NOT_LOGGED_IN);
            updateResponse.setUserDetails(null);
            return updateResponse;
        }

        boolean updated = false;
        if (updateRequest.getEmail() != null && !updateRequest.getEmail().equals(cachedUser.getEmail())) {
            cachedUser.setEmail(updateRequest.getEmail());
            updated = true;
        }
        if (updateRequest.getFirstName() != null && !updateRequest.getFirstName().equals(cachedUser.getFirstName())) {
            cachedUser.setFirstName(updateRequest.getFirstName());
            updated = true;
        }
        if (updateRequest.getLastName() != null && !updateRequest.getLastName().equals(cachedUser.getLastName())) {
            cachedUser.setLastName(updateRequest.getLastName());
            updated = true;
        }
        if (updateRequest.getMobile() != null && !updateRequest.getMobile().equals(cachedUser.getMobile())) {
            cachedUser.setMobile(updateRequest.getMobile());
            updated = true;
        }
        if (updateRequest.getPassword() != null && !updateRequest.getPassword().equals(cachedUser.getPassword())) {
            cachedUser.setPassword(updateRequest.getPassword());
            updated = true;
        }

        updateResponse.setHttpStatus(HttpStatus.OK);
        updateResponse.setUsername(cachedUser.getUsername());
        updateResponse.setMessage(USER_UPDATE_SUCCESSFUL + cachedUser.getUsername());
        updateResponse.setUserDetails(UserDto.createFrom(cachedUser));
        if (updated) {
            userRepository.save(cachedUser);
        }
        return updateResponse;
    }

    @Override
    public String logout() {
        if (cachedUser != null) {
            cachedUser = null;
        }
        return USER_LOGGED_OUT_SUCCESSFUL;
    }

    @Override
    public ResponseEntity<String> getCoins(Map<String, String> headers) {
        if (cachedUser == null) {
            return new ResponseEntity<>(USER_SHOULD_BE_LOGGED_IN, HttpStatus.BAD_REQUEST);
        }
        ResponseEntity<String> response = cryptoAPI.getCoins(headers);
        UserActivity userActivity = new UserActivity();
        userActivity.setUsername(cachedUser.getUsername());
        userActivity.setResponse(String.valueOf(response.getStatusCode().value()));
        userActivity.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
        userActivityRepository.save(userActivity);
        return response;
    }
}
