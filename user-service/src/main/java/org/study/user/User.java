package org.study.user;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.study.validator.BasicInfo;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data @Accessors(chain = true)
@ToString(exclude = {"courses", "teachersCourses"})
@EqualsAndHashCode(exclude = {"courses", "teachersCourses"})
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_seq", allocationSize = 1)
    @Column(name="user_id", nullable = false)
    private Long id;

    @NotNull(groups = BasicInfo.class)
    @NotBlank(message = "First_name is mandatory", groups = BasicInfo.class)
    @Size(max=20, groups = BasicInfo.class)
    @Column(name="first_name")
    private String firstName;

    @NotNull(groups = BasicInfo.class)
    @NotBlank(message = "Last_name is mandatory",groups = BasicInfo.class)
    @Size(max=20, groups = BasicInfo.class)
    @Column(name="last_name")
    private String lastName;

    @Column(name="role_id")
    private Role role;

    @NotNull(groups = BasicInfo.class)
    @NotBlank(message = "Login is mandatory",groups = BasicInfo.class)
    @Size(max=30, groups = BasicInfo.class)
    @Column(name = "login",unique = true)
    @Email
    private String login;

    @NotNull(groups = BasicInfo.class)
    @Size(max=30)
    @NotBlank(message = "Password is mandatory")
    private String password;

    @ElementCollection
    @CollectionTable(name = "users_courses", joinColumns = @JoinColumn(name = "userid"))
    @Column(name = "course_id")
    private List<Long> courses = new ArrayList<>();


    @Column(name="is_student")
    private boolean isStudent;

    @Column(name="is_blocked")
    private boolean isBlocked;

//    @OneToMany(mappedBy = "teacher")
//    private List<Course> teachersCourses;


}

