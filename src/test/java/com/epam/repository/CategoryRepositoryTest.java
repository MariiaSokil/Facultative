package com.epam.repository;

import com.epam.model.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findAll() {
        List<Category> categories = categoryRepository.findAll();
        assertEquals(0, categories.size());
    }

    @Test
    public void findById() {
        Category category = new Category();
        category.setId(1L);

        categoryRepository.save(category);
        assertTrue(categoryRepository.findById(1L).isPresent());
    }

    @Test
    public void createNew() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Programming");
        categoryRepository.save(category);
        assertNotNull(categoryRepository.findById(1L));
    }

    @Test
    public void deleteById() {
        Category category = new Category();
        category.setId(1L);

        categoryRepository.save(category);
        assertNotNull(categoryRepository.findById(1L));
        categoryRepository.deleteById(1L);
        assertFalse(categoryRepository.findById(1L).isPresent());
    }

    @Test
    public void updateUser() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Programming");

        categoryRepository.save(category);
        assertTrue(categoryRepository.findById(1L).isPresent());
        category.setName("Math");
        categoryRepository.save(category);

    }
}