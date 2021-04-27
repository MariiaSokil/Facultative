package com.epam.controllers;

import com.epam.controllers.assembler.CourseAssembler;
import com.epam.controllers.type.CourseType;
import com.epam.dto.CourseDTO;
import com.epam.mappers.impl.CourseMapper;
import com.epam.model.Course;
import com.epam.model.Status;
import com.epam.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.hateoas.MediaTypes.HAL_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(CourseController.class)
@RunWith(SpringRunner.class)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @MockBean
    private CourseMapper courseMapper;

    @MockBean
    private CourseAssembler courseAssembler;


    @Test
    public void findById() throws Exception {
        Course course = new Course();
        CourseDTO courseDTO=new CourseDTO();
        courseDTO.setId(1L);
        CourseType courseType=new CourseType(courseDTO);

        when(courseService.findById(1L)).thenReturn(course);
        when(courseMapper.toDTO(course)).thenReturn(courseDTO);
        when(courseAssembler.toModel(courseDTO)).thenReturn(courseType);

        mockMvc.perform(get("/courses/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(HAL_JSON))
                .andExpect(jsonPath("$.id").value("1"));

    }

    @Test
    public void createNew() throws Exception {

        CourseDTO newCourseDTO= new CourseDTO();
        newCourseDTO.setTitle("Python");
        newCourseDTO.setDuration(20);
        newCourseDTO.setPrice(2000);
        newCourseDTO.setStatus(Status.COMING_SOON);
        newCourseDTO.setEnrollment(0);

        Course course = new Course();
        when(courseMapper.toMODEL(newCourseDTO)).thenReturn(course);
        when(courseService.save(course)).thenReturn(course);
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(20L);
        CourseType courseType = new CourseType(courseDTO);
        when(courseMapper.toDTO(course)).thenReturn(courseDTO);
        when(courseAssembler.toModel(courseDTO)).thenReturn(courseType);

        mockMvc.perform(post("/courses")
                .content(asJsonString(newCourseDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());


    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void updateCourse() throws Exception {

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(1L);
        courseDTO.setTitle("Python");
        courseDTO.setDuration(20);
        courseDTO.setPrice(2000);
        courseDTO.setStatus(Status.COMING_SOON);
        courseDTO.setEnrollment(0);

       Course c = new Course();
        when(courseMapper.toMODEL(courseDTO)).thenReturn(c);

        Course course = new Course();
        course.setTitle("Java 11");
        when(courseService.updateCourse(1L, c)).thenReturn(course);
        when(courseMapper.toDTO(course)).thenReturn(courseDTO);

        CourseType courseType = new CourseType(courseDTO);
        when(courseAssembler.toModel(courseDTO)).thenReturn(courseType);

        mockMvc.perform(put("/courses/1")
                .content(asJsonString(courseDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    @Test
    public void deleteById() throws Exception {

        mockMvc.perform(delete("/courses/{id}","1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
