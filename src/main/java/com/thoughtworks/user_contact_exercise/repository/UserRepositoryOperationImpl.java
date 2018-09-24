package com.thoughtworks.user_contact_exercise.repository;

import com.thoughtworks.user_contact_exercise.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryOperationImpl implements UserRepositoryOperation {
    @Override
    public User addNewUser(User user) {
        UserStorage.getUserMap().put(user.getId(), user);
        return user;
    }

    @Override
    public Collection<User> getUsers() {
        return UserStorage.getUserMap().values();
    }

    @Override
    public List<Integer> getContactsByUserId(int userId) {
        return UserStorage.getContactsByUserId(userId);
    }

    @Override
    public void deleteContactByUserIdAndContactId(int userId, int contactId) {
        UserStorage.deleteContactsByUserId(userId, contactId);
    }

    @Override
    public User getUsersById(int userId) {
        return (User) UserStorage.getUserMap().get(userId);
    }

    @Override
    public void putContact(int userId, int contactId) {
        UserStorage.putContactByUserId(userId, contactId);
    }
}
