package com.epam.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String role;
    private String login;
    //private List<Course> courses = new ArrayList<>();
    private boolean isStudent;
    private boolean isBlocked;
}
