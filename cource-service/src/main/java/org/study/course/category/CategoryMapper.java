package org.study.course.category;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CategoryMapper{

    public CategoryDTO toDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public Category toMODEL(CategoryDTO categoryDTO) {
        return new Category()
                .setId(categoryDTO.getId())
                .setName(categoryDTO.getName());
    }

    public Collection<Category> toMODEL(Collection<CategoryDTO> dtos) {
        List<Category> categoryList = new ArrayList<>(dtos.size());
        dtos.forEach(dto -> {
            Category category = toMODEL(dto);
            categoryList.add(category);
        });
        return categoryList;
    }

    public Collection<CategoryDTO> toDTO(Collection<Category> models) {
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        models.forEach(category -> {
            CategoryDTO categoryDTO = toDTO(category);
            categoryDTOList.add(categoryDTO);
        });
        return categoryDTOList;
    }
}
