package com.example.database.repository;

import com.example.database.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {

}
