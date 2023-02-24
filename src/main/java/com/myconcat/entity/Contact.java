package com.myconcat.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "contact")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Integer id;

    @NotEmpty
    @Column(name = "name",nullable = false)
    private String name;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "user_id")
    private Integer user_id;

    public Contact(String fullname, String email, String s) {
    }
}
