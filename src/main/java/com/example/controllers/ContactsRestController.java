package com.example.controllers;


import com.example.aspect.Loggable;
import com.example.database.dto.ContactDto;
import com.example.database.entity.Contact;
import com.example.service.ContactService;
import com.example.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/contacts/")
@AllArgsConstructor
public class ContactsRestController {

    private final ContactService contactService;
    private final UserService userService;

    @Loggable
    @GetMapping(value = "list")
    public ResponseEntity<Page<ContactDto>> getContacts(
            Principal principal, Pageable pageable) {
        List<Contact> contacts = contactService.findAllByUserId(userService.findByName(principal.getName()).getId());
        List<ContactDto> contactDtos = new ArrayList<>();

        for (Contact contact : contacts) {
            contactDtos.add(ContactDto.fromContact(contact));
        }

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), contactDtos.size());
        Page<ContactDto> result
                = new PageImpl<>(contactDtos.subList(start, end), pageable, contactDtos.size());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Loggable
    @GetMapping(value = "search/{name}")
    public ResponseEntity<Page<ContactDto>> getContactByName(
            @PathVariable("name") String name, Principal principal, Pageable pageable) {
        List<Contact> contacts = contactService.findAllByUserIdAndName(userService.findByName(principal.getName()).getId(), name);
        List<ContactDto> contactDtos = new ArrayList<>();

        for (Contact contact : contacts) {
            contactDtos.add(ContactDto.fromContact(contact));
        }

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), contactDtos.size());
        Page<ContactDto> result
                = new PageImpl<>(contactDtos.subList(start, end), pageable, contactDtos.size());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Loggable
    @PostMapping(value = "newContact")
    public ResponseEntity<ContactDto> addContact(@Valid @RequestBody ContactDto contactDto, Principal principal) throws Exception {
        contactDto.setUserId(userService.findByName(principal.getName()).getId());
        contactService.addContact(contactDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Loggable
    @PutMapping(value = "{id}")
    public ResponseEntity<ContactDto> updateContact(@PathVariable("id") Long id, @Valid @RequestBody ContactDto contactDto, Principal principal) {
        contactDto.setUserId(userService.findByName(principal.getName()).getId());
        contactService.updateContact(id, contactDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Loggable
    @DeleteMapping(value = "{id}")
    public ResponseEntity<ContactDto> deleteContact(@PathVariable("id") Long id) {
        contactService.deleteContactById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Loggable
    @GetMapping(value = "{id}")
    public ResponseEntity<ContactDto> getTaskById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(ContactDto.fromContact(contactService.findById(id)), HttpStatus.OK);
    }
}
