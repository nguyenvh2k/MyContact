package com.myconcat.controller;

import com.myconcat.entity.Contact;
import com.myconcat.service.ContactService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v3")
@Tag(name = "Contact")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RestContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/contact")
    public List<Contact> listContact(){
        return (List<Contact>) contactService.findAll();
    }

    @GetMapping("/contact/{id}")
    public Optional<Contact> findContactById(@PathVariable(value = "id") Integer id){
        contactService.findOne(id);
        return contactService.findOne(id);
    }

    @GetMapping("/contact/search")
    public List<Contact> findContactByName(String name){
        return contactService.search(name);
    }

    @PostMapping("/contact")
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact){
        contactService.save(contact);
        return ResponseEntity.ok().body(contact);
    }

    @DeleteMapping("/contact/{id}")
    public ResponseEntity<Contact> deleteContact(@PathVariable(value = "id") Integer id){
        contactService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/contact/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable(value = "id") Integer id ,@RequestBody Contact contact){
        if (contact==null){
            return ResponseEntity.notFound().build();
        }
        contactService.save(contact);
        return ResponseEntity.ok().build();
    }

}
