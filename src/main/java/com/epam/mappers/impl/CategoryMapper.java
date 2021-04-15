package com.epam.mappers.impl;

import com.epam.dto.CategoryDTO;
import com.epam.mappers.BaseMapper;
import com.epam.model.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CategoryMapper implements BaseMapper<CategoryDTO, Category> {
    @Override
    public CategoryDTO toDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    @Override
    public Category toMODEL(CategoryDTO categoryDTO) {
        return Category.builder()
                .id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .build();
    }

    @Override
    public Collection<Category> toMODEL(Collection<CategoryDTO> dtos) {
        List<Category> categoryList = new ArrayList<>(dtos.size());
        dtos.forEach(dto -> {
            Category category = toMODEL(dto);
            categoryList.add(category);
        });
        return categoryList;
    }

    @Override
    public Collection<CategoryDTO> toDTO(Collection<Category> models) {
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        models.forEach(category -> {
            CategoryDTO categoryDTO = toDTO(category);
            categoryDTOList.add(categoryDTO);
        });
        return categoryDTOList;
    }
}
