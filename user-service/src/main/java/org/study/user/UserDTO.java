package org.study.user;

import lombok.*;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String role;
    private String login;
    private String password;
    //private List<Course> courses = new ArrayList<>();
    private boolean isStudent;
    private boolean isBlocked;
}
