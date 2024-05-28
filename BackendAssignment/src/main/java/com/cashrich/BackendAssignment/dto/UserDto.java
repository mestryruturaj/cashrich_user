package com.cashrich.BackendAssignment.dto;

import com.cashrich.BackendAssignment.Entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDto {
    @Pattern(regexp = "[A-Za-z]+")
    private String firstName;
    @Pattern(regexp = "[A-Za-z]+")
    private String lastName;
    @Email
    private String email;
    @Pattern(regexp = "[7-9][0-9]{9}")
    private String mobile;

    public static UserDto createFrom(User user) {
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setMobile(user.getMobile());
        return userDto;
    }
}
