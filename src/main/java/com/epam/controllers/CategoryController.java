package com.epam.controllers;

import com.epam.model.Category;
import com.epam.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServlet;
import java.util.List;

/**
 * CategoryServlet.
 * @author M.Sokil
 */

@RestController
//@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

   /* @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), categoryService.findAll());
    }*/
//---------------------------------------------------------------------
    @GetMapping("/categories")
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/categories/{id}")
    public Category findById(@PathVariable Long id) {
        return categoryService.findById(id);

    }

    @PostMapping("/categories")
    public Category createNew(@RequestBody Category newCategory) {
        return categoryService.save(newCategory);
    }

    @DeleteMapping("/categories/{id}")
    public void deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @PutMapping("/categories/{id}")
    public Category updateCategory(@PathVariable Long id, Category category) {
        return categoryService.updateCategory(id, category);
    }
}
