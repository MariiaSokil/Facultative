package com.epam.controllers;

import com.epam.controllers.assembler.CategoryAssembler;
import com.epam.controllers.type.CategoryType;
import com.epam.dto.CategoryDTO;
import com.epam.mappers.impl.CategoryMapper;
import com.epam.model.Category;
import com.epam.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Log4j2
@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    private final CategoryAssembler categoryAssembler;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/categories")
    public Collection<CategoryDTO> findAll() {
        return categoryMapper.toDTO(categoryService.findAll());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/categories/{id}")
    public CategoryType findById(@PathVariable Long id) {
        log.info("Category found by id: id{}", id);
        return categoryAssembler.toModel(categoryMapper.toDTO(categoryService.findById(id)));

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/categories")
    public CategoryType createNew(@RequestBody CategoryDTO newCategoryDto) {
        log.info("Got request for category creation:{}", newCategoryDto);
        Category category = categoryMapper.toMODEL(newCategoryDto);
        return categoryAssembler.toModel(categoryMapper.toDTO(categoryService.save(category)));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.info("Category deleted: id {}", id);
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/categories/{id}")
    public CategoryType updateCategory(@PathVariable Long id, CategoryDTO categoryDTO) {
        log.info("Category updated:{}", categoryDTO);
        Category cat = categoryMapper.toMODEL(categoryDTO);
        return categoryAssembler.toModel(categoryMapper.toDTO(categoryService.updateCategory(id, cat)));
    }
}
