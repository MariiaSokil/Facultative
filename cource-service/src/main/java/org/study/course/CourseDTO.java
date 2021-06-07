package org.study.course;

import org.study.course.status.Status;

import java.time.LocalDate;

public class CourseDTO {

    private Long id;
    private String title;
  //  private CategoryDTO category;
    private int duration;
    private LocalDate startDate;
    private Integer price;
    //   private Set<User> students = new HashSet<>();
   // private UserDTO teacher;
    private Status status;
    private int enrollment;
}
