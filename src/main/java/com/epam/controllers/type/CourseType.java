package com.epam.controllers.type;

import com.epam.dto.CourseDTO;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class CourseType extends RepresentationModel<CourseType> {

    @JsonUnwrapped
    private CourseDTO courseDTO;
}
