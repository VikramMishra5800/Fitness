package com.example.gateway.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "Email can't be blank")
    @Email(message = "Invalid Email Format.")
    private String email;

    private String keyCloakId;

    @NotBlank(message = "Password is required.")
    @Size(min = 6, message = "Password should be of atleast 6 length.")
    private String password;

    private String firstName;
    private String lastName;
}
