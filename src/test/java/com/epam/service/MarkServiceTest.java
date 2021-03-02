package com.epam.service;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class MarkServiceTest {

    private MarkService service = new MarkService();

    @Test
    public void testName() {
        assertNotNull(service.getMark());
    }
}