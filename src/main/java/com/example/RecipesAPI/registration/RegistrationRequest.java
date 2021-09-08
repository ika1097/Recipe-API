package com.example.RecipesAPI.registration;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegistrationRequest {
    @NotBlank(message = "Name is missing")
    private String firstName;
    @NotBlank(message = "Last name is missing")
    private String lastName;
    @Email(message = "Email not valid")
    @NotBlank(message = "Email is missing")
    private String email;
    @NotBlank(message = "Password is missing")
    private String password;
}
