package org.study.course;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;



    @Test
    public void findAll() {
        List<Course> courseList= Arrays.asList(new Course().setId(1L), new Course().setId(2L));
        when(courseRepository.findAll(Specification.where(null))).thenReturn(courseList);
        courseService.findAll(Specification.where(null));
        verify(courseRepository).findAll(Specification.where(null));
    }

    @Test
    public void save() {
        Course course = new Course().setId(1L);
        courseService.save(course);
        verify(courseRepository).save(course);
    }

    @Test
    public void findById() {
        Optional<Course> optionalCourse = Optional.of(new Course().setId(1L));
        when(courseRepository.findById(1l)).thenReturn(optionalCourse);
        Course result = courseService.findById(1L);
        verify(courseRepository).findById(1L);

        assertEquals(optionalCourse.get(), result);
    }
    @Test(expected = RuntimeException.class)
    public void findByIdCaseNotFound() {
        Optional<Course> optionalCourse = Optional.empty();
        when(courseRepository.findById(1L)).thenReturn(optionalCourse);
        courseService.findById(1l);
    }

    @Test
    public void updateCourse() {
        Course course = new Course().setId(1L).setTitle("Java");
        Optional<Course> optionalCourse = Optional.of(new Course().setId(1L).setTitle("OOP"));
        when(courseRepository.findById(1L)).thenReturn(optionalCourse);
        when(courseRepository.save(course)).thenReturn(course);
        courseService.updateCourse(1L,course);
        verify(courseRepository).findById(1L);
        verify(courseRepository).save(course);
    }

    @Test(expected = RuntimeException.class)
    public void updateCourseCaseNotFound() {
        Course course = new Course().setId(1L).setTitle("Java");
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());
        courseService.updateCourse(1L,course);
        verify(courseRepository).findById(1L);
        verify(courseRepository,never()).save(any());
    }

    @Test
    public void deleteById() {
        courseService.deleteById(1L);
        verify(courseRepository).deleteById(1L);
    }

    @Test
    public void testFindAll() {
    }

    @Test
    public void assignTeacherToCourse() {
    }
}