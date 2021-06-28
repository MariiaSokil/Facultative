package org.study.course.category;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void findAll() {
        List<Category> categoryList = Arrays.asList(new Category().setId(1L), new Category().setId(2L));
        when(categoryRepository.findAll()).thenReturn(categoryList);
        categoryService.findAll();
        verify(categoryRepository).findAll();
    }

    @Test
    public void save() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void updateCategory() {
    }
}