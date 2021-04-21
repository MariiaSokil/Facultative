package com.epam.controllers.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.epam.controllers.UserController;
import com.epam.controllers.type.UserType;
import com.epam.dto.UserDTO;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;


@Component
public class UserAssembler extends RepresentationModelAssemblerSupport<UserDTO, UserType> {

    public UserAssembler() {
        super(UserController.class, UserType.class);

    }

    @Override
    public UserType toModel(UserDTO entity) {
        UserType userType = new UserType(entity);

        Link get = linkTo(methodOn(UserController.class).findById(entity.getId())).withRel("findById");
        Link create = linkTo(methodOn(UserController.class).createNew(entity)).withRel("createNew");
        Link update = linkTo(methodOn(UserController.class).updateUser(entity.getId(), entity)).withRel("updateUser");
        Link delete  = linkTo(methodOn(UserController.class).deleteById(entity.getId())).withSelfRel();
        userType.add(get,create, update, delete);
        return userType;
    }
}

