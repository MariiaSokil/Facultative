package org.study.course.status;

import org.junit.Test;

import static org.junit.Assert.*;

public class StatusTest {
    @Test
    public void test() {
        assertEquals(0, Status.COMING_SOON.getId());
        assertEquals(1,  Status.ONGOING.getId());
        assertEquals(2,  Status.COMPLETED.getId());
    }

}