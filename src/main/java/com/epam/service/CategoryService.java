package com.epam.service;

import com.epam.dao.CategoryDao;

public class CategoryService {
    private final CategoryDao categoryDao;

    public CategoryService() {
        categoryDao = new CategoryDao();
    }

    public Object findAll() {
        return categoryDao.findAll();
    }
}
