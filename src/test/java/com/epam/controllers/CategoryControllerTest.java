package com.epam.controllers;

import com.epam.controllers.assembler.CategoryAssembler;
import com.epam.controllers.type.CategoryType;
import com.epam.dto.CategoryDTO;
import com.epam.mappers.impl.CategoryMapper;
import com.epam.model.Category;
import com.epam.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.hateoas.MediaTypes.HAL_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(CategoryController.class)
@RunWith(SpringRunner.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private CategoryMapper categoryMapper;

    @MockBean
    private CategoryAssembler categoryAssembler;


    @Test
    public void findById() throws Exception {
        Category category = new Category();
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        CategoryType categoryType = new CategoryType(categoryDTO);

        when(categoryService.findById(1L)).thenReturn(category);
        when(categoryMapper.toDTO(category)).thenReturn(categoryDTO);
        when(categoryAssembler.toModel(categoryDTO)).thenReturn(categoryType);

        mockMvc.perform(get("/categories/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(HAL_JSON))
                .andExpect(jsonPath("$.id").value("1"));

    }

    @Test
    public void findByIdCaseNotFound() throws Exception {
        when(categoryService.findById(1L)).thenThrow(RuntimeException.class);
        mockMvc.perform(get("/categories/1"))
                .andExpect(status().is5xxServerError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorType").value("DATABASE_ERROR_TYPE"));

    }

    @Test
    public void createNew() throws Exception {

        CategoryDTO newCategoryDTO = new CategoryDTO();
        newCategoryDTO.setName("Programming");

        Category category = new Category();
        when(categoryMapper.toMODEL(newCategoryDTO)).thenReturn(category);
        when(categoryService.save(category)).thenReturn(category);
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(5L);
        CategoryType categoryType = new CategoryType(categoryDTO);
        when(categoryMapper.toDTO(category)).thenReturn(categoryDTO);
        when(categoryAssembler.toModel(categoryDTO)).thenReturn(categoryType);

        mockMvc.perform(post("/categories")
                .content(asJsonString(newCategoryDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
