package org.study.course.category;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Log4j2
@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    private final CategoryAssembler categoryAssembler;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/categories")
    public Collection<CategoryDTO> findAll() {
        return categoryMapper.toDTO(categoryService.findAll());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/categories/{id}")
    public CategoryType findById(@PathVariable Long id) {
        Category category=categoryService.findById(id);
        CategoryDTO categoryDTO=categoryMapper.toDTO(category);
        return categoryAssembler.toModel(categoryDTO);

    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/categories")
    public CategoryType createNew(@RequestBody CategoryDTO newCategoryDto) {
        Category category = categoryMapper.toMODEL(newCategoryDto);
        category=categoryService.save(category);
        CategoryDTO categoryDTO=categoryMapper.toDTO(category);
        return categoryAssembler.toModel(categoryDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/categories/{id}")
    public CategoryType updateCategory(@PathVariable Long id, CategoryDTO categoryDTO) {
        Category cat = categoryMapper.toMODEL(categoryDTO);
        return categoryAssembler.toModel(categoryMapper.toDTO(categoryService.updateCategory(id, cat)));
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    @AllArgsConstructor
    public static class CategoryType extends RepresentationModel<CategoryType> {

        @JsonUnwrapped
        private CategoryDTO categoryDTO;
    }

}
