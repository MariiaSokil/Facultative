package org.study.course;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.study.course.category.Category;
import org.study.course.category.CategoryDTO;
import org.study.course.category.CategoryMapper;
//import org.study.user.User;
//import org.study.user.UserDTO;
//import org.study.user.UserMapper;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component @RequiredArgsConstructor
public class CourseMapper {

    private final CategoryMapper categoryMapper;
    //private final UserMapper userMapper;


    public CourseDTO toDTO(Course course) {
        CategoryDTO categoryDTO = categoryMapper.toDTO(course.getCategory());
        //UserDTO teacherDTO =userMapper.toDTO(course.getTeacher());
        return CourseDTO.builder()
                .id(course.getId())
                .title(course.getTitle())
                .category(categoryDTO)
                .duration(course.getDuration())
                .startDate(course.getStartDate())
                .price(course.getPrice())
                .status(course.getStatus())
                .enrollment(course.getEnrollment())
                .teacher(course.getTeacher())
                .build();
    }

    public Course toMODEL(CourseDTO courseDTO) {
        Category category = categoryMapper.toMODEL(courseDTO.getCategory());
        //User teacher = userMapper.toMODEL(courseDTO.getTeacher());
        return new Course()
                .setId(courseDTO.getId())
                .setTitle(courseDTO.getTitle())
                .setCategory(category)
                .setDuration(courseDTO.getDuration())
                .setStartDate(courseDTO.getStartDate())
                .setPrice(courseDTO.getPrice())
                .setStatus(courseDTO.getStatus())
                .setEnrollment(courseDTO.getEnrollment());
             //   .setTeacher(teacher);

    }

    public Collection<Course> toMODEL(Collection<CourseDTO> courseDTOS) {
        List<Course> courseList = new ArrayList<>(courseDTOS.size());
        courseDTOS.forEach(courseDTO -> {
            Course course = toMODEL(courseDTO);
            courseList.add(course);
        });
        return courseList;
    }

    public Collection<CourseDTO> toDTO(Collection<Course> courses) {
        List<CourseDTO> courseDTOList = new ArrayList<>();
        courses.forEach(course -> {
            CourseDTO courseDTO = toDTO(course);
            courseDTOList.add(courseDTO);
        });
        return courseDTOList;
    }

}
