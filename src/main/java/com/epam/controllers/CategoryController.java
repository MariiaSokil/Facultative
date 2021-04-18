package com.epam.controllers;

import com.epam.model.Category;
import com.epam.service.CategoryService;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
//@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/categories")
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/categories/{id}")
    public Category findById(@PathVariable Long id) {
        log.info("Found category by Id: id {}", id);
        return categoryService.findById(id);

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/categories")
    public Category createNew(@RequestBody Category newCategory) {
        log.info("Created newCategory: newCourse {}", newCategory);
        return categoryService.save(newCategory);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/categories/{id}")
    public void deleteById(@PathVariable Long id) {
        log.info("Deleted category by id: id {}", id);
        categoryService.deleteById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/categories/{id}")
    public Category updateCategory(@PathVariable Long id, Category category) {
        log.info("Updated course: category {}", category);
        return categoryService.updateCategory(id, category);
    }

     /* @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), categoryService.findAll());
    }*/

}
