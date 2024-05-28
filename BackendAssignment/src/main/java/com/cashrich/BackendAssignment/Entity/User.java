package com.cashrich.BackendAssignment.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @Pattern(regexp = "[A-Za-z0-9]{4,15}")
    private String username;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$")
    private String password;
    @Pattern(regexp = "[A-Za-z]+")
    private String firstName;
    @Pattern(regexp = "[A-Za-z]+")
    private String lastName;
    @Email
    private String email;
    @Pattern(regexp = "[7-9][0-9]{9}")
    private String mobile;
}
