package com.epam.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RoleTest {
    @Test
    public void test() {
        assertEquals(0, Role.STUDENT.getId());
        assertEquals(1, Role.TEACHER.getId());
        assertEquals(2, Role.ADMIN.getId());
    }
}
