package com.epam.service;

import com.epam.model.Course;

import java.util.Arrays;
import java.util.List;

public class CourseService {
    public List<Course> getAll() {
        return Arrays.asList(
                new Course(1L, "Java"),
                new Course(2L, "SpringBoot"),
                new Course(3L, "Python"),
                new Course(3L, "C++"),
                new Course(3L, "Javascript"),
                new Course(3L, "Go"),
                new Course(3L, "Scala"),
                new Course(3L, "Typescript"),
                new Course(3L, "Android"),
                new Course(3L, "JDBC"),
                new Course(3L, "Docker"),
                new Course(3L, "Azure"),
                new Course(3L, "AWS"),
                new Course(3L, ".Net"),
                new Course(3L, "NoSQLDB")
        );
    }

    public Course getCourse() {
        return new Course((long) 1, "Java");
    }
}
