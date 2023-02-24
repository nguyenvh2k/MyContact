package com.myconcat.service;

import com.myconcat.entity.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    Iterable<Contact> findAll();

    Iterable<Contact> findByUserId(Integer user_id);

    List<Contact> search(String term);

    Optional<Contact> findOne(Integer id);

    void save(Contact contact);

    void delete(Integer id);

}
