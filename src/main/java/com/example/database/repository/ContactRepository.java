package com.example.database.repository;

import com.example.database.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {

    Contact findByContactName(String name);

    List<Contact> findAllByUserId(Long id);

    List<Contact> findAllByUserIdAndContactName(Long id, String name);

    void deleteTaskById(Long id);
}
