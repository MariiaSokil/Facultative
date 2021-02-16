package com.epam.model;

import java.util.ArrayList;
import java.util.List;

/**
 * User model.
 *
 * @author M.Sokil
 *
 */

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private Role role;
    private String login;
    private String password;
    private List<Course> courses = new ArrayList<>();
    private boolean isStudent;
    private boolean isBlocked;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(Long id, String firstName, String lastName, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public User(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = Role.STUDENT;
    }

    public User(Long id, String firstName, String lastName, Role role, String login, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.login = login;
        this.password = password;
        this.courses.add(new Course(1L, "Java"));
    }

    public Long getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public Role getRole() {
        return role;
    }
    public String getLogin() { return login; }
    public String getPassword() {
        return password;
    }
    public List<Course> getCourses() {
        return courses;
    }
    public boolean isStudent() { return isStudent; }
    public boolean isBlocked() { return isBlocked; }

    public void setId(Long id) {
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
    public void setStudent(boolean student) { isStudent = student; }
    public void setBlocked(boolean blocked) { isBlocked = blocked; }
}

