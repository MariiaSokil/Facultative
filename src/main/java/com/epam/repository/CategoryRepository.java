package com.epam.repository;

import com.epam.model.Category;
import com.epam.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
   List<Category> findAll();


}
