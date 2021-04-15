package com.epam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User model.
 *
 * @author M.Sokil
 *
 */
@Data
@ToString(exclude = {"courses"})
@EqualsAndHashCode(exclude = {"courses"})

@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
//    @GeneratedValue((strategy = GenerationType.SEQUENCE))
//    @SequenceGenerator()
    @Column(name="user_id")
    private Long id;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="role_id")
    private Role role;
    private String login;
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

