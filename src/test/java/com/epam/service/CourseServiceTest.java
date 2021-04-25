package com.epam.service;

import com.epam.exception.UserNotFoundException;
import com.epam.model.Course;
import com.epam.model.User;
import com.epam.repository.CourseRepository;
import com.epam.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTest {
    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    @Test
    public void findAll() {
        courseService.findAll();
        verify(courseRepository).findAll();
    }

    @Test
    public void findById() {
        when(courseRepository.findById(2L)).thenReturn(Optional.of(new Course()));
        courseService.findById(2L);
        verify(courseRepository).findById(2L);
    }
    @Test(expected = RuntimeException.class)
    public void findByIdCaseNotFound() {
        courseService.findById(2L);
        verify(courseRepository).findById(2L);
    }

    @Test
    public void save() {
       Course course = new Course();
        courseService.save(course);
        verify(courseRepository).save(course);
    }

    @Test
    public void deleteById() {
        courseService.deleteById(3L);
        verify(courseRepository).deleteById(3L);
    }

    @Test
    public void updateUser() {
        Course courseDB = new Course().setTitle("Programming");
        Long id = new Long(1L);
        when(courseRepository.findById(id)).thenReturn(Optional.of(courseDB));
        when(courseRepository.save(any(Course.class))).thenReturn(new Course());

       Course course = new Course();
        course.setTitle("Painting");
        courseService.updateCourse(id, course);

        verify(courseRepository).findById(id);
        verify(courseRepository).save(courseDB.setTitle(course.getTitle()));
    }

    @Test(expected = RuntimeException.class)
    public void updateUserCaseNotFound() {
        Course courseDB = new Course().setTitle("Programming");
        Long id = new Long(1L);

        Course course = new Course();
        course.setTitle("Painting");
        courseService.updateCourse(id, course);
    }

    @Test
    public void findAllPage() {
        Pageable pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("firstName")));
        courseService.findAll(pageRequest);
        verify(courseRepository).findAll(pageRequest);
    }
}