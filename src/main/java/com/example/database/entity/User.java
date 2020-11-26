package com.example.database.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "USERS")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"role", "contacts"})
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", nullable = false)
    private Long id;

    @Column(name = "USER_USERNAME", nullable = false, length = 40)
    private String username;

    @Column(name = "USER_PASSWORD", length = 40, nullable = false)
    private String password;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "ROLE_ID")
    @JsonManagedReference
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonBackReference
    private Collection<Contact> contacts;

}
