package com.dreambooks.service;

import com.dreambooks.model.Book;
import com.dreambooks.model.Category;
import com.dreambooks.repository.BookRepository;
import com.dreambooks.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;
    private BookRepository bookRepository;

    public CategoryService(CategoryRepository categoryRepository, BookRepository bookRepository) {
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
    }

    public Set<Category> getAllCategories() {
        Set<Category> categories = new HashSet<>();
        categoryRepository.findAll().iterator().forEachRemaining(categories::add);

        return categories;
    }

    public Category getCategoryByDescription(String description) {
        return categoryRepository.findByDescription(description);
    }

    public void isCategoryExist(Book book, Category category) {
        Category new_category = categoryRepository.findByDescription(category.getDescription());

        if(new_category == null)
            categoryRepository.save(category);

        book.setCategory(new_category);
    }

    public Long countCategories() {
        return categoryRepository.countCategories();
    }

    public Category getCategoryById(Long id) {
        Optional<Category> optionalBook = categoryRepository.findById(id);

        return optionalBook.get();
    }

    public void deleteCategory(Long id) {
        Category category = getCategoryById(id);
        checkIfCategoryGetAnyBooks(category);
        categoryRepository.deleteById(id);
    }

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    public Set<Category> getCategoriesByDescription(String description) {
        return categoryRepository.findCategoriesWithPartOfNames(description);
    }

    public void checkIfCategoryGetAnyBooks(Category category) {
        if(!category.getBooks().isEmpty()) {

            Set<Book> books = new HashSet<>();
            Category tempCategory = createNoCategory(category);

            bookRepository.findAll().iterator().forEachRemaining(books::add);
            books.stream().filter(book -> book.getCategory().equals(category)).iterator().forEachRemaining(book -> {
                book.setCategory(tempCategory);
                bookRepository.save(book);
            });
        }
    }

    public Category createNoCategory(Category category) {
        Category noCategory = getCategoryByDescription("No category");
        if(noCategory == null) {
            noCategory = new Category();
            noCategory.setDescription("No category");
            categoryRepository.save(noCategory);
        }

        return noCategory;
    }
}
