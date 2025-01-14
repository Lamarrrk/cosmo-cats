package com.cosmocats.service;

import com.cosmocats.domain.Category;
import java.util.List;

public interface CategoryService {
    List<Category> findAllCategories();
    Category findCategoryById(long categoryId);
}

