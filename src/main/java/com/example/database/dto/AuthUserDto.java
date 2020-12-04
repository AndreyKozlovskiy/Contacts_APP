package com.example.database.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class AuthUserDto {

    @Null
    private Long userId;

    @NotNull
    @NotBlank(message = "Please, enter the userName!")
    @Size(min = 3, max = 40, message = "Username must be between 3 and 40 symbols")
    @Pattern(regexp = "^[A-z\\d*]{3,40}$")
    private String name;

    @NotNull
    @NotBlank(message = "Please, enter the password!")
    @Size(min = 8, max = 40, message = "Password must be between 8 and 40 symbols")
    private String password;
}
