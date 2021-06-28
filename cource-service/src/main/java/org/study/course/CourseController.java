package org.study.course;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.study.course.status.Status;

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
    private final UserClient userClient;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/courses")
    public Collection<CourseDTO> findAll(@RequestParam(required = false) String title,
                                         @RequestParam(required = false) Long teacher,
                                         @RequestParam(required = false) Long student,
                                         @RequestParam(required = false) Status status) {
        List<Long> list = null;
        if (student != null) {
            list = userClient.getCoursesForTheUser(student);
        }

        Specification<Course> specification =
                Specification.where(CourseSpecification.getCoursesByTitle(title))
                        .and(CourseSpecification.getCoursesByTeacher(teacher))
                        .and(CourseSpecification.getCoursesByIds(list))
                        .and(CourseSpecification.getCoursesByStatus(status));

        return courseMapper.toDTO(courseService.findAll(specification));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/courses/{id}")
    public CourseType findById(@PathVariable Long id) {
        Course course = courseService.findById(id);
        CourseDTO courseDTO = courseMapper.toDTO(course);
        return courseAssembler.toModel(courseDTO);
    }
// create new course
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/courses")
    public CourseType createNew(@RequestBody CourseDTO newCourseDto) {
        Course course = courseMapper.toMODEL(newCourseDto);
        course = courseService.save(course);
        CourseDTO courseDTO = courseMapper.toDTO(course);
        return courseAssembler.toModel(courseDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        courseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    // update the course
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/courses/{id}")
    public CourseType updateCourse(@PathVariable Long id, @RequestBody CourseDTO courseDTO) {
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
    public boolean assignTeacher2Course(@PathVariable Long courseId, @PathVariable Long teacherId, @RequestBody(required = false) CourseDTO inputDTO) {
        try {
            courseService.assignTeacherToCourse(courseId, teacherId);
            return true;
        } catch (RuntimeException e) {
            log.warn(e.getMessage());
        }
        return false;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/courses/{courseId}/active")
    public boolean isActive(@PathVariable Long courseId) {
        try {
            Course course = courseService.findById(courseId);
            log.info("Course found by id: {}", courseId);
            return course.getStatus() != Status.COMPLETED;
        } catch (RuntimeException e) {
            return false;
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/courses/{courseId}/name")
    public String getNameById(@PathVariable Long courseId) {
        try {
            Course course = courseService.findById(courseId);
            return course.getTitle();
        } catch (RuntimeException e) {
            return null;
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    @AllArgsConstructor
    public static class CourseType extends RepresentationModel<CourseType> {

        @JsonUnwrapped
        private CourseDTO courseDTO;
    }

    @FeignClient("user-service")
    interface UserClient {

        @GetMapping("/users/{id}/courses")
        List<Long> getCoursesForTheUser(@PathVariable Long id);
    }
}



