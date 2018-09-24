package com.thoughtworks.user_contact_exercise.repository;

import com.thoughtworks.user_contact_exercise.domain.User;

import java.util.Collection;
import java.util.List;

public interface UserRepositoryOperation {
    User addNewUser(User user);

    Collection getUsers();

    User getUsersById(int userId);

    void putContact(int userId, int contactId);

    List<Integer> getContactsByUserId(int userId);

    void deleteContactByUserIdAndContactId(int userId, int contactId);

}
