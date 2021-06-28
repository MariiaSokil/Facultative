package org.study.course.category;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
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
        Category category = new Category().setId(1L);
        categoryService.save(category);
        verify(categoryRepository).save(category);
    }

    @Test
    public void findById() {
        Optional<Category> optionalCategory = Optional.of(new Category().setId(1L));
        when(categoryRepository.findById(1l)).thenReturn(optionalCategory);
        Category result = categoryService.findById(1L);
        verify(categoryRepository).findById(1L);

        assertEquals(optionalCategory.get(), result);
    }

    @Test(expected = RuntimeException.class)
    public void findByIdCaseNotFound() {
        Optional<Category> optionalCategory = Optional.empty();
        when(categoryRepository.findById(1L)).thenReturn(optionalCategory);
        categoryService.findById(1l);
    }

    @Test
    public void deleteById() {
        categoryService.deleteById(1L);
        verify(categoryRepository).deleteById(1L);
    }

    @Test
    public void updateCategory() {
        Category category=new Category().setId(1L).setName("Programing");
        Optional<Category> optionalCategory = Optional.of(new Category().setId(1L).setName("Match"));
        when(categoryRepository.findById(1L)).thenReturn(optionalCategory);
        when(categoryRepository.save(category)).thenReturn(category);
        categoryService.updateCategory(1L,category);
        verify(categoryRepository).findById(1L);
        verify(categoryRepository).save(category);
    }

    @Test(expected = RuntimeException.class)
    public void updateCategoryCaseNotFound() {
        Category category=new Category().setId(1L).setName("Programing");
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());
        categoryService.updateCategory(1L,category);
        verify(categoryRepository).findById(1L);
        verify(categoryRepository,never()).save(any());
    }
}