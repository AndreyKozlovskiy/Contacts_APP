package com.example.database.repository;

import com.example.database.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;


@Repository

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
