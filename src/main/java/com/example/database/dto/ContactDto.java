package com.example.database.dto;

import com.example.database.entity.Contact;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Data
public class ContactDto {
    @Null
    private Long contactId;

    @NotBlank(message = "Contact name can not be null")
    @Size(min = 2,max=40, message = "Contact name must contains 5-40 symbols")
    private String name;

    @Size(max = 40, message = "Contact surname must contains max 40 symbols")
    private String surname;

    @NotBlank(message = "Phone can not be null")
    @Size(min = 5,max=40, message = "Phone must contains 5-40 symbols")
    private String phone;

    private Long userId;

    public static Contact toContact(ContactDto contactDto){
        Contact contact = new Contact();
        contact.setId(contactDto.getContactId());
        contact.setContactName(contactDto.getName());
        contact.setContactSurname(contactDto.getSurname());
        contact.setContactPhone(contactDto.getPhone());

        return contact;
    }

    public static ContactDto fromContact(Contact contact) {
        ContactDto contactDto = new ContactDto();
        contactDto.setContactId(contact.getId());
        contactDto.setName(contact.getContactName());
        contactDto.setSurname(contact.getContactSurname());
        contactDto.setPhone(contact.getContactPhone());

        return contactDto;
    }
}
