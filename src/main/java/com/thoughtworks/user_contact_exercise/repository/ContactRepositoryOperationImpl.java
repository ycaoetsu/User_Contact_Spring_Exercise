package com.thoughtworks.user_contact_exercise.repository;

import com.thoughtworks.user_contact_exercise.domain.Contact;
import com.thoughtworks.user_contact_exercise.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class ContactRepositoryOperationImpl implements ContactRepositoryOperation {
    @Override
    public List<Contact> getContactsByIds(List<Integer> userContacts) {
        return ContactStorage.getContacts().stream().map(contactId -> ContactStorage.getContactMap().get(contactId)).collect(Collectors.toList());
    }

    @Override
    public Contact addContact(Contact contact) {
        ContactStorage.getContactMap().put(contact.getId(), contact);
        return contact;
    }

    @Override
    public void addContact(Contact... contacts) {
        Arrays.stream(contacts).forEach(contact -> ContactStorage.getContactMap().put(contact.getId(), contact));
    }


    @Override
    public Contact updateContact(Contact contact) {
        ContactStorage.getContactMap().put(contact.getId(), contact);
        return contact;
    }

    @Override
    public void deleteContactByContactId(int contactId) {
        ContactStorage.removeContact(contactId);
    }

    @Override
    public Contact getContactByNameInContacts(List<Integer> contacts, String contactName) {
        return ContactStorage.getContactByContactsAndName(contacts, contactName);
    }
}
