package org.study.course;

import org.springframework.hateoas.Link;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class CourseAssembler extends RepresentationModelAssemblerSupport<CourseDTO, CourseController.CourseType> {

    public CourseAssembler() {
        super(CourseController.class, CourseController.CourseType.class);

    }

    @Override
    public CourseController.CourseType toModel(CourseDTO entity) {
        CourseController.CourseType courseType = new CourseController.CourseType(entity);

        Link get = linkTo(methodOn(CourseController.class).findById(entity.getId())).withRel("findById");
        Link create = linkTo(methodOn(CourseController.class).createNew(entity)).withRel("createNew");
        Link update = linkTo(methodOn(CourseController.class).updateCourse(entity.getId(), entity)).withRel("updateUser");
        Link delete  = linkTo(methodOn(CourseController.class).deleteById(entity.getId())).withSelfRel();
        courseType.add( get,create, update, delete);
        return courseType;
    }
}

