package com.epam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Data @Accessors(chain = true)
@ToString(exclude = {"courses"})
@EqualsAndHashCode(exclude = {"courses"})
@Entity
@Table(name = "users")
public class User {
    @Id
    @NotNull
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
//    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq")
    @Column(name="user_id")
    private Long id;

    @NotNull
    @NotBlank(message = "First_name is mandatory")
    @Size(max=20)
    @Column(name="first_name")
    private String firstName;

    @NotNull
    @NotBlank(message = "Last_name is mandatory")
    @Size(max=20)
    @Column(name="last_name")
    private String lastName;

    @Column(name="role_id")
    private Role role;

    @NotNull
   @NotBlank(message = "Login is mandatory")
    @Size(max=30)
    @Column(name = "login",unique = true)
    @Email
    private String login;

    @NotNull
    @Size(max=30)
    @NotBlank(message = "Password is mandatory")
    private String password;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "users_courses",
            joinColumns = { @JoinColumn(name = "userid") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") })
    private List<Course> courses = new ArrayList<>();

    @Column(name="is_student")
    private boolean isStudent;

    @Column(name="is_blocked")
    private boolean isBlocked;
}

