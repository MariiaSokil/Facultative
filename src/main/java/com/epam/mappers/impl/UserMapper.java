package com.epam.mappers.impl;

import com.epam.dto.UserDTO;
import com.epam.mappers.BaseMapper;
import com.epam.model.Role;
import com.epam.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class UserMapper implements BaseMapper<UserDTO, User> {
    @Override
    public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole().name())
                .login(user.getLogin())
                .isStudent(user.isStudent())
                .isBlocked(user.isBlocked())
                .build();
    }

    @Override
    public User toMODEL(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .role(Role.valueOf(userDTO.getRole()))
                .login(userDTO.getLogin())
                .isStudent(userDTO.isStudent())
                .isBlocked(userDTO.isBlocked())
                .build();
    }

    @Override
    public Collection<User> toMODEL(Collection<UserDTO> dtos) {
        List<User> userList = new ArrayList<>(dtos.size());
        dtos.forEach(dto -> {
            User user = toMODEL(dto);
            userList.add(user);
        });
        return userList;
    }

    @Override
    public Collection<UserDTO> toDTO(Collection<User> models) {
        List<UserDTO> userDTOList = new ArrayList<>();
        models.forEach(user -> {
            UserDTO userDTO = toDTO(user);
            userDTOList.add(userDTO);
        });
        return userDTOList;
    }
}
