package org.study.course;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CourseSpecification {
    public static Specification<Course> getCoursesByTitle(String title) {
        return (root, query, criteriaBuilder) ->
                Optional.ofNullable(title)
                .map(val -> criteriaBuilder.equal(root.get(Course_.title), val))
                .orElse(null);
    }

    public static Specification<Course> getCoursesByTeacher(Long teacher) {
        return (root, query, criteriaBuilder) ->
                Optional.ofNullable(teacher)
                        .map(val -> criteriaBuilder.equal(root.get(Course_.teacher), val))
                        .orElse(null);
    }


}
