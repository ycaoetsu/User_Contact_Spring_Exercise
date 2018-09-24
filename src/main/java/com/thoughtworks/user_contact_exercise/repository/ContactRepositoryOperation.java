package com.thoughtworks.user_contact_exercise.repository;

import com.thoughtworks.user_contact_exercise.domain.Contact;
import com.thoughtworks.user_contact_exercise.domain.User;

import java.util.List;

public interface ContactRepositoryOperation {
    List<Contact> getContactsByIds(List<Integer> userContacts);

    Contact addContact(Contact contact);

    void addContact(Contact ... contacts);

    Contact updateContact(Contact contact);

    void deleteContactByContactId(int contactId);

    Contact getContactByNameInContacts(List<Integer> contacts, String contactName);
}
