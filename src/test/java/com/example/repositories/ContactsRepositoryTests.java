package com.example.repositories;

import com.example.database.repository.ContactRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContactsRepositoryTests {

    @Autowired
    private ContactRepository contactRepository;


    @Test
    public void getTestById() {
        Assert.assertNotNull(contactRepository.findByContactName("Ann"));
    }
}
