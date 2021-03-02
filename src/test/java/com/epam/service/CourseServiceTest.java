package com.epam.service;

import com.epam.dao.CourseDao;
import com.epam.model.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTest {
    @Mock
    private CourseDao courseDao;

    @InjectMocks
    private CourseService service;

    @Test
    public void findAll() {
        service.findAll(true);
        verify(courseDao).findAll(true);
    }

    @Test
    public void updateCourse() {
        Course course = new Course();
        service.updateCourse(course, true);
        verify(courseDao).updateCourse(course, true);
    }

    @Test
    public void updateCourseCaseSimple() {
        Course course = new Course();
        service.updateCourse(course);
        verify(courseDao).updateCourse(course);
    }

    @Test
    public void findAllByStudentId() {
        service.findAllByStudentId(1L);
        verify(courseDao).findAllByStudentId(1L);
    }

    @Test
    public void deleteCourse() {
        service.deleteCourse(1L, true);
        verify(courseDao).deleteCourse(1L, true);
    }

    @Test
    public void saveNew() {
        Course course = new Course();
        service.saveNew(course);
        verify(courseDao).saveNew(course);
    }
}