package org.study.user;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.ListJoin;
import java.util.Optional;

@Component
public class UserSpecification {

    public static Specification<User> getUsersByCourse(Long courseId) {
        return (root, query, criteriaBuilder) ->
                Optional.ofNullable(courseId)
                        .map(val -> {
                            ListJoin<User, Long> join = root.join(User_.courses);
                            return criteriaBuilder.equal(join, courseId);
                        })
                        .orElse(null);
    }
}
