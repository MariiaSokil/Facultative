package com.epam.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StatusTest {
    @Test
    public void test() {
        assertEquals(0, Status.COMING_SOON.getId());
        assertEquals(1,  Status.ONGOING.getId());
        assertEquals(2,  Status.COMPLETED.getId());
    }
}
