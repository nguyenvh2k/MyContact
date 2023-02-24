package com.myconcat.repository;

import com.myconcat.entity.Contact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact,Integer> {

    List<Contact> findByNameContaining(String term);

    @Query(value = "select * from contact ct where ct.user_id = ?1", nativeQuery = true)
    List<Contact> findByUser_id(Integer user_id);
}
