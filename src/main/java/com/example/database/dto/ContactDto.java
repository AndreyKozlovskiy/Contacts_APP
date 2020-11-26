package com.example.database.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto {
    @NotBlank(message = "Contact name can not be null")
    @Size(min = 5, message = "Contact name must contains at least 5 symbols")
    private String name;

    private String surname;

    @NotBlank(message = "Phone can not be null")
    private String phone;
}
