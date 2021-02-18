package com.epam.service;

import com.epam.dao.CourseDao;
import com.epam.model.Course;

import java.util.List;

public class CourseService {

    private final CourseDao courseDao;

    public CourseService() {
        courseDao = new CourseDao();
    }

    public List<Course> getAll(boolean eagerStudents) {
        return courseDao.findAll(eagerStudents);
        //return courseDao.findAllWithLazyStudents();
    }

    public Course getCourse() {
        //TODO check if we need that
        return new Course((long) 1, "Java");
    }

    public void updateCourse(Course course, boolean bindUser){
        courseDao.updateCourse(course, bindUser);
    }

    public List<Course> findAllByStudentId(Long studentId) {
        return courseDao.findAllByStudentId(studentId);
    }
}
