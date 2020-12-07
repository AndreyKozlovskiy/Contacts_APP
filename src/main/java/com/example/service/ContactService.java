package com.example.service;

import com.example.aspect.Loggable;
import com.example.database.dto.ContactDto;
import com.example.database.entity.Contact;
import com.example.database.repository.ContactRepository;
import com.example.database.repository.UserRepository;

import com.example.service.contracts.IContactService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ContactService implements IContactService {
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;

    public ContactService(ContactRepository taskRepository, UserRepository userRepository) {
        this.contactRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Contact findById(Long id) {
        return contactRepository.findById(id).get();
    }

    @Override
    @Loggable
    public Contact findByName(String name) {
        return contactRepository.findByContactName(name);
    }

    @Override
    @Loggable
    public List<Contact> findAllByUserId(Long id) {
        return contactRepository.findAllByUserId(id);
    }

    @Override
    @Loggable
    public List<Contact> findAllByUserIdAndName(Long id, String name) {
        return contactRepository.findAllByUserIdAndContactName(id, name);
    }

    @Override
    @Loggable
    @Transactional
    public void deleteContactById(Long id) {
        contactRepository.deleteTaskById(id);
    }

    @Override
    @Loggable
    public void addContact(ContactDto contactDto) {
        Contact contact = ContactDto.toContact(contactDto);
        contact.setUser(userRepository.findById(contactDto.getId()).get());

        contactRepository.save(contact);
    }

    @Override
    @Loggable
    public void updateContact(Long id, ContactDto contact) {
        Contact updatedContact = contactRepository.findById(id).get();
        updatedContact.setUser(userRepository.findById(contact.getUserId()).get());

        updatedContact.setContactName(contact.getName());
        updatedContact.setContactSurname(contact.getPhone());
        updatedContact.setContactPhone(contact.getPhone());

        contactRepository.save(updatedContact);
    }

}
