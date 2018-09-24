package com.thoughtworks.user_contact_exercise.repository;

import com.thoughtworks.user_contact_exercise.domain.Contact;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactStorage {
    private static final Map<Integer, Contact> CONTACT_MAP = new HashMap<>();

    public static void clear() {
        CONTACT_MAP.clear();
    }

    public static Collection<Contact> getContacts() {
        return CONTACT_MAP.values();
    }

    public static Map<Integer, Contact> getContactMap() {
        return CONTACT_MAP;
    }

    public static int getSize() {
        return CONTACT_MAP.size();
    }

    public static void removeContact(int contactId) {
        CONTACT_MAP.remove(contactId);
    }

    public static Contact getContactByContactsAndName(List<Integer> contacts, String contactName) {
        return contacts.stream().map(contact -> CONTACT_MAP.get(contact)).filter(contact -> contact.getName().equals(contactName)).findFirst().get();
    }
}
