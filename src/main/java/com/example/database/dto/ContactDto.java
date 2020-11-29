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
    @Size(min = 5,max=40, message = "Contact name must contains 5-40 symbols")
    private String name;

    @Size(max = 40, message = "Contact surname must contains max 40 symbols")
    private String surname;

    @NotBlank(message = "Phone can not be null")
    @Size(min = 5,max=40, message = "Phone must contains 5-40 symbols")
    private String phone;
}
