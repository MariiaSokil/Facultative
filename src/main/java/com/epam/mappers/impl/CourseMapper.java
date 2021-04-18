package com.epam.mappers.impl;

import com.epam.dto.CategoryDTO;
import com.epam.dto.CourseDTO;
import com.epam.dto.UserDTO;
import com.epam.mappers.BaseMapper;
import com.epam.model.Category;
import com.epam.model.Course;
import com.epam.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component @RequiredArgsConstructor
public class CourseMapper implements BaseMapper<CourseDTO, Course> {

    private final CategoryMapper categoryMapper;
    private final UserMapper userMapper;

    @Override
    public CourseDTO toDTO(Course course) {
        CategoryDTO categoryDTO = categoryMapper.toDTO(course.getCategory());
        UserDTO teacherDTO =userMapper.toDTO(course.getTeacher());
        return CourseDTO.builder()
                .id(course.getId())
                .title(course.getTitle())
                .category(categoryDTO)
                .duration(course.getDuration())
                .startDate(course.getStartDate())
                .price(course.getPrice())
                .status(course.getStatus())
                .enrollment(course.getEnrollment())
                .teacher(teacherDTO)
                .build();
    }

    @Override
    public Course toMODEL(CourseDTO courseDTO) {
        Category category = categoryMapper.toMODEL(courseDTO.getCategory());
        User teacher = userMapper.toMODEL(courseDTO.getTeacher());
        return new Course()
                .setId(courseDTO.getId())
                .setTitle(courseDTO.getTitle())
                .setCategory(category)
                .setDuration(courseDTO.getDuration())
                .setStartDate(courseDTO.getStartDate())
                .setPrice(courseDTO.getPrice())
                .setStatus(courseDTO.getStatus())
                .setEnrollment(courseDTO.getEnrollment())
                .setTeacher(teacher);

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
