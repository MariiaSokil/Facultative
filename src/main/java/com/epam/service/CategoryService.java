package com.epam.service;

import com.epam.dao.CategoryDao;
import com.epam.model.Category;
import com.epam.model.Course;
import com.epam.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * CategoryService.
 * @author M.Sokil
 */
@Service
public class CategoryService {
    private final CategoryDao dao;

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryService() {
        dao = new CategoryDao();
    }
    //for Mockito testing
    public CategoryService(CategoryDao dao) {
        this.dao = dao;
    }

    /**
     * Return List of categories.
     * @return list of categories.
     */
    public List<Category> findAll() {
        return dao.findAll();
    }
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

}
