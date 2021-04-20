package com.epam.controllers;

import com.epam.dto.CategoryDTO;
import com.epam.mappers.impl.CategoryMapper;
import com.epam.model.Category;
import com.epam.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Log4j2
@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/categories")
    public Collection<CategoryDTO> findAll() {
        return categoryMapper.toDTO(categoryService.findAll());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/categories/{id}")
    public CategoryDTO findById(@PathVariable Long id) {
        log.info("Category found by id: id{}", id);
        return categoryMapper.toDTO(categoryService.findById(id));

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/categories")
    public CategoryDTO createNew(@RequestBody CategoryDTO newCategoryDto) {
        log.info("Got request for category creation:{}", newCategoryDto);
        Category category = categoryMapper.toMODEL(newCategoryDto);
        return categoryMapper.toDTO(categoryService.save(category));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/categories/{id}")
    public void deleteById(@PathVariable Long id) {
        log.info("Category deleted: id {}", id);
        categoryService.deleteById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/categories/{id}")
    public CategoryDTO updateCategory(@PathVariable Long id, CategoryDTO categoryDTO) {
        log.info("Category updated:{}", categoryDTO);
        Category cat = categoryMapper.toMODEL(categoryDTO);
        return categoryMapper.toDTO(categoryService.updateCategory(id, cat));
    }
}
