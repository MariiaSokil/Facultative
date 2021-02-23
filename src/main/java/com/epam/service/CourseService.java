package com.epam.service;

import com.epam.dao.CourseDao;
import com.epam.model.Course;

import java.util.List;

public class CourseService {

    private final CourseDao courseDao;

    public CourseService() {
        courseDao = new CourseDao();
    }

    public List<Course> findAll(boolean eagerStudents) {
        return courseDao.findAll(eagerStudents);
    }

    public void updateCourse(Course course, boolean bindUser){
        courseDao.updateCourse(course, bindUser);
    }

    public void updateCourse(Course course){
        courseDao.updateCourse(course);
    }

    public List<Course> findAllByStudentId(Long studentId) {
        return courseDao.findAllByStudentId(studentId);
    }

    public void deleteCourse(Long courseId, boolean removeAssociations)  {
        courseDao.deleteCourse(courseId, removeAssociations);
    }

    public void saveNew(Course course) {
        courseDao.saveNew(course);
    }
}
