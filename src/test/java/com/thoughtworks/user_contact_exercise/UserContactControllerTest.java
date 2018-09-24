package com.thoughtworks.user_contact_exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.user_contact_exercise.controller.UserContactController;
import com.thoughtworks.user_contact_exercise.domain.Contact;
import com.thoughtworks.user_contact_exercise.domain.User;
import com.thoughtworks.user_contact_exercise.repository.ContactRepositoryOperationImpl;
import com.thoughtworks.user_contact_exercise.repository.ContactStorage;
import com.thoughtworks.user_contact_exercise.repository.UserRepositoryOperationImpl;
import com.thoughtworks.user_contact_exercise.repository.UserStorage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
public class UserContactControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = standaloneSetup(new UserContactController()).build();
        UserStorage.clear();
        ContactStorage.clear();
    }

    @AfterEach
    public void tearDown() {
        UserStorage.clear();
        ContactStorage.clear();
    }

    @Test
    void should_get_all_users() throws Exception {
        new UserRepositoryOperationImpl().addNewUser(new User(1, "Kei"));
        new UserRepositoryOperationImpl().addNewUser(new User(2, "Kenty"));
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void should_create_a_new_user() throws Exception {
        new UserRepositoryOperationImpl().addNewUser(new User(1, "Kei"));
        int previousSize = UserStorage.getSize();
        User newUser = new User(2, "Kenty");
        mockMvc.perform(post("/api/users")
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(newUser)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("2"))
                .andExpect(jsonPath("$.name").value("Kenty"));
        int currentSize = UserStorage.getSize();
        assertThat(currentSize).isEqualTo(previousSize + 1);
    }

    @Test
    void should_create_contact_for_specific_user() throws Exception {
        new UserRepositoryOperationImpl().addNewUser(new User(5, "Kei"));
        Contact contact = new Contact(1, "Kenty", 24, "male", "09012345678902");
        mockMvc.perform(post("/api/users/5/contacts")
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(contact)))
                .andExpect(status().isCreated());
        assertThat(ContactStorage.getContacts().size()).isEqualTo(1);
    }

    @Test
    void should_get_all_contact_of_a_specific_user() throws Exception {

        User newUser = new User(5, "Kei");
        new UserRepositoryOperationImpl().addNewUser(newUser);
        Contact contact0 = new Contact(1, "Kenty", 24, "male", "09012345678902");
        Contact contact1 = new Contact(2, "Kento", 28, "male", "09012345678901");
        newUser.putContact(contact0.getId());
        newUser.putContact(contact1.getId());
        new ContactRepositoryOperationImpl().addContact(contact0, contact1);

        mockMvc.perform(get("/api/users/5/contacts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

        assertThat(ContactStorage.getSize()).isEqualTo(2);
    }

    @Test
    void should_update_contact_for_specific_user() throws Exception {
        User user = new User(5, "Kei");
        new UserRepositoryOperationImpl().addNewUser(user);
        Contact contact0 = new Contact(1, "Kenty", 24, "male", "09012345678902");
        Contact contact1 = new Contact(2, "Kento", 28, "male", "09012345678901");
        new ContactRepositoryOperationImpl().addContact(contact0, contact1);
        Contact updateContact = new Contact(2, "Kento", 26, "male", "09012345678901");

        mockMvc.perform(put("/api/users/5/contacts")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(updateContact)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Kento"))
                .andExpect(jsonPath("$.mobile").value("09012345678901"))
                .andExpect(jsonPath("$.age").value(26));
    }

    @Test
    void should_delete_contact_of_user() throws Exception {
        User user = new User(5, "Kei");
        new UserRepositoryOperationImpl().addNewUser(user);
        Contact contact0 = new Contact(1, "Kenty", 24, "male", "09012345678902");
        Contact contact1 = new Contact(2, "Kento", 28, "male", "09012345678901");
        new ContactRepositoryOperationImpl().addContact(contact0, contact1);
        user.putContact(contact0.getId());
        user.putContact(contact1.getId());
        mockMvc.perform(get("/api/users/5/contacts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
        mockMvc.perform(delete("/api/users/5/contacts/1"))
                .andExpect(status().isNoContent());
        mockMvc.perform(get("/api/users/5/contacts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void should_get_contact_of_a_specific_user() throws Exception {
        User user = new User(1, "sjyuan");
        new UserRepositoryOperationImpl().addNewUser(user);
        Contact contact = new Contact(1, "kaiguang", 80, "male", "12222222222");
        user.putContact(contact.getId());
        new ContactRepositoryOperationImpl().addContact(contact);
        mockMvc.perform(get("/api/users/1/contacts/kaiguang"))
                .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("kaiguang"))
        .andExpect(jsonPath("$.age").value(80));
    }
}
