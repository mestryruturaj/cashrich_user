package com.cashrich.BackendAssignment.service;

import com.cashrich.BackendAssignment.Entity.User;
import com.cashrich.BackendAssignment.dto.SignUpRequest;
import com.cashrich.BackendAssignment.dto.SignUpResponse;
import com.cashrich.BackendAssignment.dto.UserDto;
import com.cashrich.BackendAssignment.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.cashrich.BackendAssignment.constants.Constants.USER_CREATION_SUCCESS;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
