package com.epam.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class CategoryDTO {

    private Long id;
    private String name;
   // private List<Course> courses;
}
