package org.study.course;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;
    private final CourseAssembler courseAssembler;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/courses")
    public Collection<CourseDTO> findAll() {
        return courseMapper.toDTO(courseService.findAll());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/courses/{id}")
    public CourseType findById(@PathVariable Long id) {
        log.info("Course found by id: id{}", id);
        Course course= courseService.findById(id);
        CourseDTO courseDTO= courseMapper.toDTO(course);
        return courseAssembler.toModel(courseDTO);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/courses")
    public CourseType createNew(@RequestBody CourseDTO newCourseDto) {
        log.info("Got request for course creation :{}", newCourseDto);
        Course course = courseMapper.toMODEL(newCourseDto);
        course =courseService.save(course);
        CourseDTO courseDTO = courseMapper.toDTO(course);
        return courseAssembler.toModel(courseDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.info("Course deleted: id {}", id);
        courseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/courses/{id}")
    public CourseType updateCourse(@PathVariable Long id, @RequestBody CourseDTO courseDTO) {
        log.info("Course updated:{}", courseDTO);
        Course course = courseMapper.toMODEL(courseDTO);
        return courseAssembler.toModel(courseMapper.toDTO(courseService.updateCourse(id, course)));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/courses/page")
    public Page<CourseDTO> getPage(int pageNum, int size) {
        Pageable pageRequest = PageRequest.of(pageNum, size, Sort.by(Sort.Order.asc("title")));
        Page<Course> page = courseService.findAll(pageRequest);
        Collection<CourseDTO> dtos = courseMapper.toDTO(page.getContent());
        List<CourseDTO> listDtos = new ArrayList<>(dtos);
        return new PageImpl<>(listDtos, pageRequest, page.getTotalElements());
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/courses/{courseId}/teacher/{teacherId}")
    public CourseType assignCourseToTeacher(@PathVariable Long courseId, @PathVariable Long teacherId, @RequestBody(required = false) CourseDTO inputDTO)  {
        log.info("Got request for teacher assign to course:{}", courseId);
        Course course =courseService.assignTeacherToCourse(courseId, teacherId);
        CourseDTO courseDTO = courseMapper.toDTO(course);
        return courseAssembler.toModel(courseDTO);
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    @AllArgsConstructor
    public static class CourseType extends RepresentationModel<CourseType> {

        @JsonUnwrapped
        private CourseDTO courseDTO;
    }
}



