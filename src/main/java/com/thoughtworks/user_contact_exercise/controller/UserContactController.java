package com.thoughtworks.user_contact_exercise.controller;

import com.thoughtworks.user_contact_exercise.domain.Contact;
import com.thoughtworks.user_contact_exercise.domain.User;
import com.thoughtworks.user_contact_exercise.repository.ContactRepositoryOperation;
import com.thoughtworks.user_contact_exercise.repository.ContactRepositoryOperationImpl;
import com.thoughtworks.user_contact_exercise.repository.UserRepositoryOperation;
import com.thoughtworks.user_contact_exercise.repository.UserRepositoryOperationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserContactController {
//    @Autowired
    private UserRepositoryOperation userRepositoryOperation = new UserRepositoryOperationImpl();
//    @Autowired
    private ContactRepositoryOperation contactRepositoryOperation = new ContactRepositoryOperationImpl();

    @GetMapping("/users")
    public ResponseEntity getAllUsers() {
        return new ResponseEntity(userRepositoryOperation.getUsers(), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity addNewUser(@RequestBody User user) {
        return new ResponseEntity(userRepositoryOperation.addNewUser(user), HttpStatus.CREATED);
    }
    //"/api/users/5/contacts"
    @PostMapping("/users/{userId}/contacts")
    public ResponseEntity addNewUser(@PathVariable int userId, @RequestBody Contact contact) {
        userRepositoryOperation.putContact(userId, contact.getId());
        return new ResponseEntity(contactRepositoryOperation.addContact(contact), HttpStatus.CREATED);
    }

    @GetMapping("/users/{userId}/contacts")
    public ResponseEntity getContactByUser(@PathVariable int userId) {
        List<Integer> userContacts = userRepositoryOperation.getContactsByUserId(userId);
        List<Contact> contacts = contactRepositoryOperation.getContactsByIds(userContacts);
        return new ResponseEntity(contacts, HttpStatus.OK);
    }
    @PutMapping("/users/{userId}/contacts")
    public ResponseEntity updateUserContact(@PathVariable int userId, @RequestBody Contact contact) {
        return new ResponseEntity(contactRepositoryOperation.updateContact(contact), HttpStatus.OK);
    }
    @DeleteMapping("/users/{userId}/contacts/{contactId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContactOfUser(@PathVariable int userId, @PathVariable int contactId) {
        userRepositoryOperation.deleteContactByUserIdAndContactId(userId, contactId);
        contactRepositoryOperation.deleteContactByContactId(contactId);
    }
    //"/api/users/1/contacts/kaiguang"
    @GetMapping("/users/{userId}/contacts/{contactName}")
    public ResponseEntity<Contact> getContactByContactNameAndUser(@PathVariable int userId, @PathVariable String contactName) {
        List<Integer> contacts = userRepositoryOperation.getContactsByUserId(userId);
        Contact contact = contactRepositoryOperation.getContactByNameInContacts(contacts, contactName);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }
}
