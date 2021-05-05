package com.epam.repository;

import com.epam.model.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void findAll() {
        List<Course> courses = courseRepository.findAll();
        assertEquals(0, courses.size());
    }

    @Test
    public void findById() {
        Course course= new Course();
        course.setId(1L);

        courseRepository.save(course);
        assertTrue(courseRepository.findById(1L).isPresent());
    }

    @Test
    public void createNew() {
        Course course= new Course();
        course.setId(1L);
        course.setTitle("Java 8");
        courseRepository.save(course);
        assertNotNull(courseRepository.findById(1L));
    }

    @Test
    public void deleteById() {
        Course course= new Course();
        course.setId(1L);

        courseRepository.save(course);
        assertNotNull(courseRepository.findById(1L));
        courseRepository.deleteById(1L);
        assertFalse(courseRepository.findById(1L).isPresent());
    }

    @Test
    public void updateUser() {
        Course course= new Course();
        course.setId(1L);
        course.setTitle("Java 8");

        courseRepository.save(course);
        assertTrue(courseRepository.findById(1L).isPresent());
        course.setTitle("Math");
        courseRepository.save(course);

    }
}