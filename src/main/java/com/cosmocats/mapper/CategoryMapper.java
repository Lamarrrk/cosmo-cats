package com.cosmocats.mapper;

import com.cosmocats.domain.Category;
import com.cosmocats.dto.category.CategoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toDTO(Category category);

    Category toDomain(CategoryDTO categoryDTO);
}


