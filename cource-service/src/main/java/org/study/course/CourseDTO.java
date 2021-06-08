package org.study.course;

import lombok.*;
import org.study.course.category.CategoryDTO;
import org.study.course.status.Status;


import java.time.LocalDate;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    private Long id;
    private String title;
    private CategoryDTO category;
    private int duration;
    private LocalDate startDate;
    private Integer price;
    //   private Set<User> students = new HashSet<>();
   // private UserDTO teacher;
    private Status status;
    private int enrollment;
}
