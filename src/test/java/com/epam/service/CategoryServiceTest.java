package com.epam.service;

import com.epam.model.Category;
import com.epam.repository.CategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void findAll() {
        categoryService.findAll();
        verify(categoryRepository).findAll();
    }

    @Test
    public void findById() {
        when(categoryRepository.findById(2L)).thenReturn(Optional.of(new Category()));
        categoryService.findById(2L);
        verify(categoryRepository).findById(2L);
    }
    @Test(expected = RuntimeException.class)
    public void findByIdCaseNotFound() {
        categoryService.findById(2L);
        verify(categoryRepository).findById(2L);
    }

    @Test
    public void save() {
       Category category =new Category();
        categoryService.save(category);
        verify(categoryRepository).save(category);
    }

    @Test
    public void deleteById() {
        categoryService.deleteById(3L);
        verify(categoryRepository).deleteById(3L);
    }

    @Test
    public void updateUser() {
        Category categoryDB = new Category().setName("Programming");
        Long id = new Long(1L);
        when(categoryRepository.findById(id)).thenReturn(Optional.of(categoryDB));
        when(categoryRepository.save(any(Category.class))).thenReturn(new Category());

        Category category =new Category();
        category.setName("Painting");
        categoryService.updateCategory(id, category);

        verify(categoryRepository).findById(id);
        verify(categoryRepository).save(categoryDB.setName(category.getName()));
    }

    @Test(expected = RuntimeException.class)
    public void updateUserCaseNotFound() {
        Category categoryDB = new Category().setName("Programming");
        Long id = new Long(1L);

        Category category =new Category();
        category.setName("Painting");
        categoryService.updateCategory(id, category);
    }
}