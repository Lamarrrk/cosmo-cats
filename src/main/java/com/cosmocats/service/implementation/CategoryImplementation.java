package com.cosmocats.service.implementation;

import com.cosmocats.domain.Category;
import com.cosmocats.exceptions.CategoryException;
import com.cosmocats.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryImplementation implements CategoryService {

    private final List<Category> categoryList = new ArrayList<>(
            List.of(
                    Category.builder().id(1L).name("Galaxy Toys").build(),
                    Category.builder().id(2L).name("Star Cats").build(),
                    Category.builder().id(3L).name("Cosmic Pet Apparel").build()
            )
    );

    @Override
    public List<Category> findAllCategories() {
        return categoryList;
    }

    @Override
    public Category findCategoryById(long categoryId) {
        return categoryList.stream()
                .filter(category -> category.getId() == categoryId)
                .findFirst()
                .orElseThrow(() -> new CategoryException(categoryId));
    }
}

