package org.study.course;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.PathVariable;
import org.study.course.status.Status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CourseControllerTest {

    @InjectMocks
    private CourseController courseController;

    @Mock
    private CourseService courseService;

    @Mock
    private CourseMapper courseMapper;

    @Mock
    private CourseAssembler courseAssembler;

    @Test
    public void findAll() {
    }

    @Test
    public void findById() {
       Course course = new Course();
       CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(1L);
        CourseController.CourseType courseType=new CourseController.CourseType(courseDTO);

        when(courseService.findById(1L)).thenReturn(course);
        when(courseMapper.toDTO(course)).thenReturn(courseDTO);
        when(courseAssembler.toModel(courseDTO)).thenReturn(courseType);
        courseController.findById(1L);
        verify(courseService).findById(1L);
    }

    @Test
    public void createNew() {
        CourseDTO newCourseDTO= new CourseDTO();
        newCourseDTO.setId(1L);

        Course course = new Course();
        when(courseMapper.toMODEL(newCourseDTO)).thenReturn(course);
        when(courseService.save(course)).thenReturn(course);

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(20L);
        CourseController.CourseType courseType = new CourseController.CourseType(courseDTO);
        when(courseMapper.toDTO(course)).thenReturn(courseDTO);
        when(courseAssembler.toModel(courseDTO)).thenReturn(courseType);

        courseController.createNew(newCourseDTO);
        verify(courseService).save(course);
    }

    @Test
    public void deleteById() {
        courseController.deleteById(1L);
        verify(courseService).deleteById(1L);
    }

    @Test
    public void updateCourse() {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(1L);

        Course c = new Course();
        when(courseMapper.toMODEL(courseDTO)).thenReturn(c);

        Course course = new Course();
        course.setTitle("Java 8");
        when(courseService.updateCourse(1L, c)).thenReturn(course);
        when(courseMapper.toDTO(course)).thenReturn(courseDTO);

        CourseController.CourseType courseType = new CourseController.CourseType(courseDTO);
        when(courseAssembler.toModel(courseDTO)).thenReturn(courseType);

        courseController.updateCourse(1L,courseDTO);
        verify(courseService).updateCourse(1L,c);
    }


    @Test
    public void isActive() {
        Course course = new Course().setId(1L);
        courseController.isActive(1L);
        when(courseService.findById(1L)).thenReturn(course);
        verify(courseService).findById(1L);
        assertNotNull(courseService.findById(1L));
    }

    @Test
    public void getNameById() {
        Course course = new Course().setId(1L);
        courseController.getNameById(1L);
        when(courseService.findById(1L)).thenReturn(course);
        verify(courseService).findById(1L);
        assertNotNull(courseService.findById(1L));
    }
}