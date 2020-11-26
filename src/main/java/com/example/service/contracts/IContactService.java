package com.example.service.contracts;

import com.example.database.dto.ContactDto;
import com.example.database.entity.Contact;

import java.util.Collection;

public interface IContactService {
    Collection<Contact> GetContactsByUser(Integer userId);
    Contact GetContactById(Integer id);
    Contact GetContactByPhone(String phone);
    Contact GetContactByName(String name);
    Contact GetContactBySurname(String surname);

    void saveContact(ContactDto contactDto);

    void DeleteContact(Integer id);

}
