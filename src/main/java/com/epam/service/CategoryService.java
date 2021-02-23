package com.epam.service;

import com.epam.dao.CategoryDao;
import com.epam.model.Category;

import java.util.List;

public class CategoryService {
    private final CategoryDao dao;

    public CategoryService() {
        dao = new CategoryDao();
    }
    //for Mockito testing
    public CategoryService(CategoryDao dao) {
        this.dao = dao;
    }

    public List<Category> findAll() {
        return dao.findAll();
    }
}
