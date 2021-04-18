package com.epam.dto;

import com.epam.model.Category;
import com.epam.model.Status;
import com.epam.model.User;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@Builder
@EqualsAndHashCode
public class CourseDTO {

    private Long id;
    private String title;
    private CategoryDTO category;
    private int duration;
    private LocalDate startDate;
    private Integer price;
 //   private Set<User> students = new HashSet<>();
    private UserDTO teacher;
    private Status status;
    private int enrollment;
}
