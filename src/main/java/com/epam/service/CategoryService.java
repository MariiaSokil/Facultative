package com.epam.service;

import com.epam.dao.CategoryDao;
import com.epam.model.Category;
import com.epam.repository.CategoryRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * CategoryService.
 * @author M.Sokil
 */
@Log4j2
@Service
public class CategoryService {
    private final CategoryDao dao;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category with id=" + id + " not found"));
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    public Category updateCategory(Long id, Category category) {
        return categoryRepository.findById(id)
                .map(categoryFromDB -> {
                    categoryFromDB.setName(category.getName());
                    return categoryRepository.save(categoryFromDB);
                })
                .orElseThrow(() -> new RuntimeException("Category with id=" + id + " not found"));
    }

    //---------------------------------------------------------------
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
   /* public List<Category> findAll() {
        return dao.findAll();
    }*/



}
