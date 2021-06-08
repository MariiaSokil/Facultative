package org.study.course.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.study.course.validator.BasicInfo;

import java.util.List;

/**
 * CategoryService.
 * @author M.Sokil
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * Return List of categories.
     * @return list of categories.
     */
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category save(@Validated(BasicInfo.class) Category category) {
        return categoryRepository.save(category);
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category with id=" + id + " not found"));
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    public Category updateCategory(Long id, @Validated(BasicInfo.class) Category category) {
        return categoryRepository.findById(id)
                .map(categoryFromDB -> {
                    categoryFromDB.setName(category.getName());
                    return categoryRepository.save(categoryFromDB);
                })
                .orElseThrow(() -> new RuntimeException("Category with id=" + id + " not found"));
    }
}
