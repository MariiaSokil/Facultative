package com.epam.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryTest {

    @Test
    public void getters() {
        Category category = new Category();
        category.setName("PROGRAMMING");
        category.setId(1L);

        assertEquals("PROGRAMMING", category.getName());
        assertEquals(new Long(1), category.getId());
    }
}