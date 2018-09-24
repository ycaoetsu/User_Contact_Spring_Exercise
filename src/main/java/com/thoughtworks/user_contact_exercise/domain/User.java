package com.thoughtworks.user_contact_exercise.domain;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String name;
    private List<Integer> contacts = new ArrayList<>();


    public User() {
    }
    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getContacts() {
        return contacts;
    }

    public void setContacts(List<Integer> contacts) {
        this.contacts = contacts;
    }

    public void putContact(int contactId) {
        contacts.add(contactId);
    }

    public void deleteContactById(Integer contactId) {
        contacts.remove(contactId);
    }
}
