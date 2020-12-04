package com.example.database.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegisterUserDto {
    @NotBlank(message = "Enter the username")
    @Size(min = 3, max = 40,message = "Username must be between 3 and 40 symbols")
    private String name;

    @Size(min = 8, max = 40,message = "Password must be between 8 and 20 symbols")
    @NotBlank(message = "Enter the password")
    private String password;

    @NotBlank(message = "Enter email")
    @Email(message = "Wrong email address")
    private String email;

    @Size(min = 8, max = 40,message = "Password must be between 8 and 20 symbols")
    private String repeatPassword;
}