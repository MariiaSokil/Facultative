package com.epam.repository;

import com.epam.model.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Long> {
   List<Course> findAll();
  // List<Course> findByCategoryId(Long category_id);



}
