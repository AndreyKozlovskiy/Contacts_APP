package com.example.repositories;

import com.example.database.repository.RoleRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoleRepositoryTests {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void getRoleUser() {
        Assert.assertNotNull(roleRepository.findByName("user"));
    }

    @Test
    public void getRoleAdmin() {
        Assert.assertNotNull(roleRepository.findByName("admin"));
    }
}
