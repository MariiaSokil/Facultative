package com.epam.controllers;

import com.epam.model.Category;
import com.epam.model.Course;
import com.epam.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * CategoryServlet.
 * @author M.Sokil
 */

/*@WebServlet(name = "CategoryServlet", urlPatterns = "/categories")*/
@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    private final CategoryService categoryService;

    /*public CategoryServlet() {
        categoryService = new CategoryService();
    }*/

    /*public CategoryServlet(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), categoryService.findAll());
    }*/
    @GetMapping
    public List<Category> getAll() {
        return categoryService.getAll();
    }
}
