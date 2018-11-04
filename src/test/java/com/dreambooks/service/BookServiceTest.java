package com.dreambooks.service;

import com.dreambooks.model.Author;
import com.dreambooks.model.Book;
import com.dreambooks.model.Category;
import com.dreambooks.model.Publisher;
import com.dreambooks.repository.BookRepository;
import com.dreambooks.repository.BookmarkRepository;
import com.dreambooks.repository.CategoryRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private BookService bookService;


    private Book book;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnAllBooks() {
        Iterable<Book> booksData = new LinkedList<>();
        ((LinkedList<Book>) booksData).add(book);

        when(bookRepository.findAll()).thenReturn(booksData);

        Set<Book> books = bookService.getAllBooks();

        assertEquals(1, books.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    public void shouldReturnBookById() {
        book = new Book();
        book.setId(1L);
        Optional<Book> bookOptional = Optional.of(book);

        when(bookRepository.findById(1L)).thenReturn(bookOptional);

        assertEquals(book, bookService.getBookById(1L));
    }

    @Test
    public void shouldDeleteBookById() {
        bookService.deleteBook(1l);
        verify(bookRepository, times(1)).deleteById(1L);
    }

    @Test
    public void theNumbersOfBooksShouldBeTwo() {
        when(bookRepository.countBooks()).thenReturn(2L);
        assertEquals(new Long(2L), bookService.countAllBooks());
    }

    @Test
    public void shouldReturnRandomBook() {
        when(bookRepository.getRandomBook()).thenReturn(new Book());
        assertNotNull(bookService.getRandomBook());
    }

    @Test
    public void shouldGetBooksByTitle() {
        book = new Book();
        book.setTitle("Book");
        Set<Book> books = new HashSet<>();
        books.add(book);
        when(bookRepository.findBooksWithPartOfNames("Book")).thenReturn(books);

        assertEquals(books, bookService.getBooksByTitle("Book"));
    }

    @Test
    public void shouldGetBooksByCategory() {
        Category category = new Category();
        Set<Book> books = new HashSet<>();
        books.add(new Book());
        category.setBooks(books);

        when(categoryService.getCategoryByDescription("CLASSIC")).thenReturn(category);

        Set<Book> booksReturned = bookService.getBooksByCategory("CLASSIC");
        assertEquals(books, booksReturned);
    }


}