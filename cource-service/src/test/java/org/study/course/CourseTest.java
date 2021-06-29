package org.study.course;

import org.junit.Test;
import org.study.course.category.Category;
import org.study.course.status.Status;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;

public class CourseTest {
    @Test
    public void getters() {
        Course course = new Course()
                .setId(1L)
                .setTitle("Java 8")
                .setCategory(new Category().setId(1L))
                .setDuration(12)
                .setStartDate(LocalDate.now())
                .setPrice(1200)
                .setStatus(Status.COMING_SOON)
                .setEnrollment(1);

        assertEquals(new Long(1), course.getId());
        assertEquals("Java 8", course.getTitle());
    //    assertEquals(new Category(), course.getCategory());
        assertEquals(12, course.getDuration());
        assertEquals((Integer) 1200, course.getPrice());
        assertEquals(Status.COMING_SOON, course.getStatus());
        assertEquals(1, course.getEnrollment());
        //  assertEquals(teacher, course.getTeacher());
        assertEquals(LocalDate.now(), course.getStartDate());
    }

}