package org.study.course.category;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    @Mock
    private CategoryMapper categoryMapper;

    @Mock
    private CategoryAssembler categoryAssembler;

    @Test
    public void findAll() {
        List<Category> categoryList= Arrays.asList(new Category().setId(1L), new Category().setId(2L));
        Collection<CategoryDTO> collectionDTO =new ArrayList<>();

        when(categoryService.findAll()).thenReturn(categoryList);
        when(categoryMapper.toDTO(categoryList)).thenReturn(collectionDTO);

        categoryController.findAll();
        verify(categoryService).findAll();
        verify(categoryMapper).toDTO(categoryList);
    }

    @Test
    public void findById() {
        Category category= new Category();
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        CategoryController.CategoryType userType=new CategoryController.CategoryType(categoryDTO);

        when(categoryService.findById(1L)).thenReturn(category);
        when(categoryMapper.toDTO(category)).thenReturn(categoryDTO);
        when(categoryAssembler.toModel(categoryDTO)).thenReturn(userType);
        categoryController.findById(1L);
        verify(categoryService).findById(1L);
    }

    @Test
    public void createNew() {
        CategoryDTO newCategoryDTO= new CategoryDTO();
        newCategoryDTO.setId(1L);

        Category category= new Category();
        when(categoryMapper.toMODEL(newCategoryDTO)).thenReturn(category);
        when(categoryService.save(category)).thenReturn(category);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(20L);
        CategoryController.CategoryType categoryType = new CategoryController.CategoryType(categoryDTO);
        when(categoryMapper.toDTO(category)).thenReturn(categoryDTO);
        when(categoryAssembler.toModel(categoryDTO)).thenReturn(categoryType);

        categoryController.createNew(newCategoryDTO);
        verify(categoryMapper).toMODEL(newCategoryDTO);
        verify(categoryService).save(category);
  //      verify(categoryMapper.toDTO(category));
        verify(categoryAssembler).toModel(categoryDTO);


    }

    @Test
    public void deleteById() {
        categoryController.deleteById(1L);
        verify(categoryService).deleteById(1L);
    }

    @Test
    public void updateCategory() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);

        Category cat = new Category();
        when(categoryMapper.toMODEL(categoryDTO)).thenReturn(cat);

        Category category= new Category();
        category.setName("Programing");
        when(categoryService.updateCategory(1L, cat)).thenReturn(category);
        when(categoryMapper.toDTO(category)).thenReturn(categoryDTO);

        CategoryController.CategoryType categoryType = new CategoryController.CategoryType(categoryDTO);
        when(categoryAssembler.toModel(categoryDTO)).thenReturn(categoryType);

        categoryController.updateCategory(1L,categoryDTO);

        verify(categoryMapper).toMODEL(categoryDTO);
        verify(categoryService).updateCategory(1L,cat);
        verify(categoryMapper).toDTO(category);
        verify(categoryAssembler).toModel(categoryDTO);

    }
}