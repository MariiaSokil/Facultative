package com.epam.mappers.impl;

import com.epam.dto.CourseDTO;
import com.epam.dto.UserDTO;
import com.epam.mappers.BaseMapper;
import com.epam.model.Course;
import com.epam.model.Role;
import com.epam.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CourseMapper implements BaseMapper<CourseDTO, Course> {
    @Override
    public CourseDTO toDTO(Course course) {
        return CourseDTO.builder()
                .id(course.getId())
                .title(course.getTitle())
                .category(course.getCategory())
                .duration(course.getDuration())
                .startDate(course.getStartDate())
                .price(course.getPrice())
                .status(course.getStatus())
                .enrollment(course.getEnrollment())
                .build();
    }

    @Override
    public Course toMODEL(CourseDTO courseDTO) {
        return Course.builder()
                .id(courseDTO.getId())
                .title(courseDTO.getTitle())
                .category(courseDTO.getCategory())
                .duration(courseDTO.getDuration())
                .startDate(courseDTO.getStartDate())
                .price(courseDTO.getPrice())
                .status(courseDTO.getStatus())
                .enrollment(courseDTO.getEnrollment())
                .build();
    }

    @Override
    public Collection<Course> toMODEL(Collection<CourseDTO> courseDTOS) {
        List<Course> courseList = new ArrayList<>(courseDTOS.size());
        courseDTOS.forEach(courseDTO -> {
            Course course = toMODEL(courseDTO);
            courseList.add(course);
        });
        return courseList;
    }

    @Override
    public Collection<CourseDTO> toDTO(Collection<Course> courses) {
        List<CourseDTO> courseDTOList = new ArrayList<>();
        courses.forEach(course -> {
            CourseDTO courseDTO = toDTO(course);
            courseDTOList.add(courseDTO);
        });
        return courseDTOList;
    }

}
