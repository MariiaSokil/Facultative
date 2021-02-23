package com.epam.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryTest {

    @Test
    public void getters() {
        Category testObject = new Category();
        testObject.setName("xx");
        testObject.setId(1L);
        assertEquals("xx", testObject.getName());
        assertEquals(new Long(1), testObject.getId());
    }
}