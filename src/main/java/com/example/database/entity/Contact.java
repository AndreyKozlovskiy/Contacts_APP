package com.example.database.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "CONTACTS")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CONTACT_ID", nullable = false)
    private Long id;

    @Column(name = "CONTACT_NAME", nullable = false)
    private String contactName;

    @Column(name = "CONTACT_SURNAME", nullable = true)
    private String contactSurname;

    @Column(name = "CONTACT_PHONE",nullable = false)
    private String contactPhone;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_ID")
    @JsonManagedReference
    private User user;
}
