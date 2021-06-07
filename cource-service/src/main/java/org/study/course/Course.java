package org.study.course;

public class Course {

    private Long id;

    private String firstName;

    private String lastName;

   // private Role role;

    private String login;

    private String password;

    /*@ManyToMany
    @JoinTable(name = "users_courses",
            joinColumns = { @JoinColumn(name = "userid") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") })
    private List<Course> courses = new ArrayList<>();*/

    private boolean isStudent;

    private boolean isBlocked;

   /* @OneToMany(mappedBy = "teacher")
    private List<Course> teachersCourses;*/
}

