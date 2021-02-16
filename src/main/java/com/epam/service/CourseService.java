package com.epam.service;

import com.epam.dao.CourseDao;
import com.epam.model.Course;

import java.util.List;

public class CourseService {
    public List<Course> getAll() {
        return new CourseDao().findAllWithLazyStudents();
    }

    public Course getCourse() {
        //TODO check if we need that
        return new Course((long) 1, "Java");
    }

    public void updateCourse(Course course){
        new CourseDao().updateCourse(course);
    }
}
