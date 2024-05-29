package com.cashrich.BackendAssignment.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SignUpRequest {
    @NotBlank
    @Pattern(regexp = "[A-Za-z0-9]{4,15}", message = "${username.validation}")
    private String username;
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$", message = "${password.validation}")
    private String password;
    @Pattern(regexp = "[A-Za-z]*")
    private String firstName;
    @Pattern(regexp = "[A-Za-z]*")
    private String lastName;
    @Email
    private String email;
    @Pattern(regexp = "([7-9][0-9]{9})?")
    private String mobile;
}
