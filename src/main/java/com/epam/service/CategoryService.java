package com.epam.service;

import com.epam.dao.CategoryDao;
import com.epam.model.Category;

import java.util.List;
/**
 * CategoryService.
 * @author M.Sokil
 */
public class CategoryService {
    private final CategoryDao dao;

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
}
