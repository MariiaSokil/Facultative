package com.epam.service;

import com.epam.model.Course;
import com.epam.repository.CourseRepository;
import com.epam.validator.BasicInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
/**
 * CourseService.
 * @author M.Sokil
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    @Transactional
    public List<Course> findAll(){
        return courseRepository.findAll();
    }

    public Course save(@Validated(BasicInfo.class) Course course) {
        return courseRepository.save(course);
    }

    public Course findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(RuntimeException::new); //new CourseNotFoundException(id))
    }

    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    public Course updateCourse(Long id, @Validated(BasicInfo.class) Course course) {
        return courseRepository.findById(id)
                .map(courseFromDB -> {
                    courseFromDB.setTitle(course.getTitle());
                    courseFromDB.setCategory(course.getCategory());
                    courseFromDB.setDuration(course.getDuration());
                    courseFromDB.setStartDate(course.getStartDate());
                    courseFromDB.setPrice(course.getPrice());
                    courseFromDB.setStatus(course.getStatus());
                    courseFromDB.setEnrollment(course.getEnrollment());
                    return courseRepository.save(courseFromDB);
                })
                .orElseThrow(() -> new RuntimeException("Course with id=" + id + " not found"));
    }


    public Page<Course> findAll(Pageable pageRequest) {
        return courseRepository.findAll(pageRequest);
    }
}
