package com.epam.service;

import com.epam.dao.CourseDao;
import com.epam.model.Course;
import com.epam.model.User;
import com.epam.repository.CourseRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * CourseService.
 * @author M.Sokil
 */
@Log4j2
@Service
public class CourseService {

    private final CourseDao courseDao;

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> findAll(){
        return courseRepository.findAll();
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public Course findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(RuntimeException::new); //new CourseNotFoundException(id))
    }

    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    public Course updateCourse(Long id, Course course) {
        return courseRepository.findById(id)
                .map(courseFromDB -> {
                    courseFromDB.setTitle(course.getTitle());
                    courseFromDB.setCategory(course.getCategory());
                    courseFromDB.setDuration(course.getDuration());
                    courseFromDB.setStartDate(course.getStartDate());
                    courseFromDB.setPrice(course.getPrice());
                    courseFromDB.setStatus(course.getStatus());
                    courseFromDB.setEnrollment(course.getEnrollment());
                    return courseRepository.save(courseFromDB);
                })
                .orElseThrow(() -> new RuntimeException("Course with id=" + id + " not found"));
    }



//-----------------------------------------------------------------

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

    /*  *//**
     * Save a new course.
     // *@param course Course.
     *//*
    public void saveNew(Course course) {
        courseDao.saveNew(course);
    }*/



}
