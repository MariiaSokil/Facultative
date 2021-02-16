package com.epam.service;

import com.epam.dao.CourseDao;
import com.epam.model.Course;

import java.util.List;

public class CourseService {
    public List<Course> getAll() {
        return new CourseDao().findAllWithLazyStudents();
       /* return Arrays.asList(
                new Course(1L, "Java"),
                new Course(2L, "SpringBoot"),
                new Course(3L, "Python"),
                new Course(4L, "C++"),
                new Course(5L, "Javascript"),
                new Course(6L, "Go"),
                new Course(7L, "Scala"),
                new Course(8L, "Typescript"),
                new Course(9L, "Android"),
                new Course(10L, "JDBC"),
                new Course(11L, "Docker"),
                new Course(12L, "Azure"),
                new Course(12L, "AWS"),
                new Course(14L, ".Net"),
                new Course(15L, "NoSQLDB")

       );*/
    }

    public Course getCourse() {
        return new Course((long) 1, "Java");
    }
}
