package org.study.course.category;


import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class CategoryAssembler extends RepresentationModelAssemblerSupport<CategoryDTO, CategoryController.CategoryType> {

    public CategoryAssembler() {
        super(CategoryController.class, CategoryController.CategoryType.class);

    }

    @Override
    public CategoryController.CategoryType toModel(CategoryDTO entity) {
        CategoryController.CategoryType categoryType = new CategoryController.CategoryType(entity);

        Link get = linkTo(methodOn(CategoryController.class).findById(entity.getId())).withRel("findById");
        Link create = linkTo(methodOn(CategoryController.class).createNew(entity)).withRel("createNew");
        Link update = linkTo(methodOn(CategoryController.class).updateCategory(entity.getId(), entity)).withRel("updateUser");
        Link delete  = linkTo(methodOn(CategoryController.class).deleteById(entity.getId())).withSelfRel();
        categoryType.add( get,create, update, delete);
        return categoryType;
    }
}

