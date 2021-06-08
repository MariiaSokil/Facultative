package org.study.course;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
   List<Course> findAll();
  // List<Course> findByCategoryId(Long category_id);



}
