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
        return new User()
                .setId(userDTO.getId())
                .setFirstName(userDTO.getFirstName())
                .setLastName(userDTO.getLastName())
                .setRole(Role.valueOf(userDTO.getRole()))
                .setLogin(userDTO.getLogin())
                .setPassword(userDTO.getPassword())
                .setStudent(userDTO.isStudent())
                .setBlocked(userDTO.isBlocked());
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
