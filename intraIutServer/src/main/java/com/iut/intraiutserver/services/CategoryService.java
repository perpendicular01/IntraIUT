package com.iut.intraiutserver.services;

import com.iut.intraiutserver.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    // Create a new category
    CategoryDto createCategory(CategoryDto categoryDto);

    // Update an existing category
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    // Delete a category
    void deleteCategory(Integer categoryId);

    // Get a single category by its ID
    CategoryDto getCategory(Integer categoryId);

    // Get a list of all categories
    List<CategoryDto> getAllCategories();
}