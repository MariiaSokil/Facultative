package org.study.user;


import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class UserAssembler extends RepresentationModelAssemblerSupport<UserDTO, UserController.UserType> {

    public UserAssembler() {
        super(UserController.class, UserController.UserType.class);

    }

    @Override
    public UserController.UserType toModel(UserDTO entity) {
        UserController.UserType userType = new UserController.UserType(entity);

        Link get = linkTo(methodOn(UserController.class).findById(entity.getId())).withRel("findById");
        Link create = linkTo(methodOn(UserController.class).createNew(entity)).withRel("createNew");
        Link update = linkTo(methodOn(UserController.class).updateUser(entity.getId(), entity)).withRel("updateUser");
        Link delete  = linkTo(methodOn(UserController.class).deleteById(entity.getId())).withSelfRel();
        userType.add(get,create, update, delete);
        return userType;
    }
}

