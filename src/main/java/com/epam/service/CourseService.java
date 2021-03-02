package com.epam.service;

import com.epam.dao.CourseDao;
import com.epam.model.Course;

import java.util.List;
/**
 * CourseService.
 * @author M.Sokil
 */
public class CourseService {

    private final CourseDao courseDao;

    public CourseService() {
        courseDao = new CourseDao();
    }

    public CourseService(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    /**
     * Return List of courses.
     * If flag eagerStudents true, each course contains List of students.
     * If flag false, List of student is not completed
     * @param eagerStudents  boolean.
     * @return list of courses.
     */
    public List<Course> findAll(boolean eagerStudents) {
        return courseDao.findAll(eagerStudents);
    }

    /**
     * Update course.
     * If bindUser true, then add User to course.
     * If bindUser false, then delete User from course.
     * @param course Course.
     * @param bindUser  boolean.
     */
    public void updateCourse(Course course, boolean bindUser){
        courseDao.updateCourse(course, bindUser);
    }

    /**
     * Update course.
     * @param course Course.
     */
    public void updateCourse(Course course){
        courseDao.updateCourse(course);
    }

    /**
     * Return List of courses by Student id.
     * @param studentId  Long.
     * @return list of courses.
     */
    public List<Course> findAllByStudentId(Long studentId) {
        return courseDao.findAllByStudentId(studentId);
    }

    /**
     * Delete course.
     * If removeAssociations true, then delete all Users from course.
     * If removeAssociations false, just delete course.
     * @param courseId Long.
     * @param removeAssociations  boolean.
     */
    public void deleteCourse(Long courseId, boolean removeAssociations)  {
        courseDao.deleteCourse(courseId, removeAssociations);
    }

    /**
     * Save a new course.
     *@param course Course.
     */
    public void saveNew(Course course) {
        courseDao.saveNew(course);
    }
}
