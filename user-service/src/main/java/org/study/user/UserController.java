package org.study.user;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final UserAssembler userAssembler;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users")
    public Collection<UserDTO> findAll() {
        return userMapper.toDTO(userService.findAll());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{id}")
    public UserType findById(@PathVariable Long id) {
        log.info("User found by id: id {}", id);
        User user = userService.findById(id);
        UserDTO userDto = userMapper.toDTO(user);
        return userAssembler.toModel(userDto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users")
    public UserType createNew(@RequestBody UserDTO newUserDto) {
        log.info("Got request for user creation:{}", newUserDto);
        User user = userMapper.toMODEL(newUserDto);
        user = userService.save(user);
        UserDTO userDTO = userMapper.toDTO(user);
        return userAssembler.toModel(userDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.info("User deleted: id {}", id);
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/users/{id}")
    public UserType updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        log.info("User updated:{}", userDTO);
        User u = userMapper.toMODEL(userDTO);
        return userAssembler.toModel(userMapper.toDTO(userService.updateUser(id, u)));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/page")
    public Page<UserDTO> getPage(int pageNum, int size) {
        Pageable pageRequest = PageRequest.of(pageNum, size, Sort.by(Sort.Order.asc("firstName")));
        Page<User> page = userService.findAll(pageRequest);
        Collection<UserDTO> dtos = userMapper.toDTO(page.getContent());
        List<UserDTO> listDtos = new ArrayList<>(dtos);
        return new PageImpl<>(listDtos, pageRequest, page.getTotalElements());
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    @AllArgsConstructor
    public static class UserType extends RepresentationModel<UserType> {

        @JsonUnwrapped
        private UserDTO userDTO;
    }
}
