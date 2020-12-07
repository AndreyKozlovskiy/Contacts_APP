package com.example.database.dto;

import com.example.database.entity.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto {
    @Null
    private Long id;

    @NotBlank(message = "Contact name can not be null")
    @Size(min = 5,max=40, message = "Contact name must contains 5-40 symbols")
    private String name;

    @Size(max = 40, message = "Contact surname must contains max 40 symbols")
    private String surname;

    @NotBlank(message = "Phone can not be null")
    @Size(min = 5,max=40, message = "Phone must contains 5-40 symbols")
    private String phone;

    private Long userId;

    public static Contact toContact(ContactDto taskDto){
        Contact contact = new Contact();
        contact.setId(taskDto.getId());
        contact.setContactName(taskDto.getName());
        contact.setContactSurname(taskDto.getSurname());
        contact.setContactPhone(taskDto.getPhone());

        return contact;
    }

    public static ContactDto fromContact(Contact contact) {
        ContactDto contactDto = new ContactDto();
        contactDto.setId(contact.getId());
        contactDto.setName(contact.getContactName());
        contactDto.setSurname(contact.getContactSurname());
        contactDto.setPhone(contact.getContactPhone());

        return contactDto;
    }
}
