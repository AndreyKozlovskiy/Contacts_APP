package com.example.service.contracts;

import com.example.aspect.Loggable;
import com.example.database.dto.ContactDto;
import com.example.database.entity.Contact;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

public interface IContactService {
    Contact findById(Long id);

    @Loggable
    Contact findByName(String name);

    @Loggable
    List<Contact> findAllByUserId(Long id);

    @Loggable
    List<Contact> findAllByUserIdAndName(Long id, String name);

    @Loggable
    @Transactional
    void deleteContactById(Long id);

    @Loggable
    void addContact(ContactDto contactDto);

    @Loggable void updateContact(Long id, ContactDto contact);
}
