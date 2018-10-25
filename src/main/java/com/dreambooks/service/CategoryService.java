package com.dreambooks.service;

import com.dreambooks.model.Book;
import com.dreambooks.model.Category;
import com.dreambooks.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Set<Category> getAllCategories() {
        Set<Category> categories = new HashSet<>();
        categoryRepository.findAll().iterator().forEachRemaining(categories::add);

        return categories;
    }

    public Category getCategoryByDescription(String description) {
        return categoryRepository.findByDescription(description);
    }
}
