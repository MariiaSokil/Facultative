package com.epam.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void getters() {
        User user = new User();
        user.setId(2L);
        user.setFirstName("Ivanov");
        user.setLastName("Ivan");
        user.setRole(Role.STUDENT);
        user.setLogin("ivan@gmail.com");
        user.setPassword("12qwe");
        user.setStudent(true);
        user.setBlocked(false);

        assertEquals(new Long(2), user.getId());
        assertEquals("Ivanov", user.getFirstName());
        assertEquals(Role.STUDENT, user.getRole());
        assertEquals("ivan@gmail.com", user.getLogin());
        assertEquals("12qwe", user.getPassword());
        assertTrue(user.isStudent());
        assertFalse(user.isBlocked());

    }
}


