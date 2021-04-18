package com.epam.controllers;

import com.epam.model.Category;
import com.epam.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/categories")
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/categories/{id}")
    public Category findById(@PathVariable Long id) {
        return categoryService.findById(id);

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/categories")
    public Category createNew(@RequestBody Category newCategory) {
        return categoryService.save(newCategory);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/categories/{id}")
    public void deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/categories/{id}")
    public Category updateCategory(@PathVariable Long id, Category category) {
        return categoryService.updateCategory(id, category);
    }
}
