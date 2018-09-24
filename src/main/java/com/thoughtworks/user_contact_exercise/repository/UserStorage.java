package com.thoughtworks.user_contact_exercise.repository;

import com.thoughtworks.user_contact_exercise.domain.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserStorage {
    private static final Map<Integer, User> USER_MAP = new HashMap<>();

    public static void clear() {
        USER_MAP.clear();
    }

    public static <Integer, User> Map getUserMap() {
        return USER_MAP;
    }

    public static int getSize() {
        return USER_MAP.size();
    }

    public static void putContactByUserId(int userId, int contactId) {
        USER_MAP.get(userId).putContact(contactId);
    }

    public static List<Integer> getContactsByUserId(int userId) {
        return USER_MAP.get(userId).getContacts();
    }

    public static void deleteContactsByUserId(int userId, int contactId) {
        USER_MAP.get(userId).deleteContactById(contactId);
    }
}
