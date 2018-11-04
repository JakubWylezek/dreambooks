package com.dreambooks.service;

import com.dreambooks.model.Book;
import com.dreambooks.model.Category;
import com.dreambooks.repository.BookRepository;
import com.dreambooks.repository.CategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private CategoryService categoryService;

    private Category category;

    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnAllCategories() {
        List<Category> categoryData = new LinkedList<>();
        categoryData.add(category);

        when(categoryRepository.findAll()).thenReturn(categoryData);

        Set<Category> categories = categoryService.getAllCategories();

        assertEquals(1, categories.size());
        verify(categoryRepository, times(1)).findAll();
    }


    @Test
    public void shouldReturnCategoryByDescription() {
        category = new Category();
        category.setDescription("CLASSIC");

        when(categoryRepository.findByDescription("CLASSIC")).thenReturn(category);

        assertEquals(category, categoryService.getCategoryByDescription("CLASSIC"));
    }

    @Test
    public void shouldSaveTheCategoryOnlyOnce() {
        categoryRepository.save(category);
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    public void theNumberOfCategoriesShouldBeTwo() {
        when(categoryRepository.countCategories()).thenReturn(new Long(2));
        assertEquals(new Long(2L), categoryService.countCategories());
    }

    @Test
    public void shouldCreateNoCategoryIfNoCategoryDoesNotExist() {

        when(categoryRepository.findByDescription("No category")).thenReturn(null);
        category = categoryService.createNoCategory(category);

        assertEquals("No category", category.getDescription());
        verify(categoryRepository, times(1)).findByDescription("No category");
    }

    @Test
    public void shouldReturnCategoryById() {
        category = new Category();
        category.setId(1L);
        category.setDescription("CLASSIC");
        Optional<Category> categoryOptional = Optional.of(category);

        when(categoryRepository.findById(anyLong())).thenReturn(categoryOptional);

        Category categoryReturned = categoryService.getCategoryById(1L);
        assertEquals("CLASSIC", categoryReturned.getDescription());
    }

    @Test
    public void shouldDeleteCategoryOnlyOnce() {
        category = new Category();
        category.setId(1L);
        Set<Book> books = new HashSet<>();
        books.add(new Book());
        category.setBooks(books);
        Optional<Category> categoryOptional = Optional.of(category);

        when(categoryRepository.findById(1L)).thenReturn(categoryOptional);
        categoryService.deleteCategory(1L);

        verify(categoryRepository, times(1)).deleteById(1L);
    }

    @Test
    public void ifCategoryHasAnyBooksThenFindAllBooks() {
        category = new Category();
        category.setId(1L);
        Set<Book> books = new HashSet<>();
        books.add(new Book());
        category.setBooks(books);

        categoryService.checkIfCategoryGetAnyBooks(category);

        verify(bookRepository, times(1)).findAll();
    }

}