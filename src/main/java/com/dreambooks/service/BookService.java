package com.dreambooks.service;

import com.dreambooks.model.Author;
import com.dreambooks.model.Book;
import com.dreambooks.model.Category;
import com.dreambooks.model.Publisher;
import com.dreambooks.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class BookService {

    private BookRepository bookRepository;
    private AuthorService authorService;
    private PublisherService publisherService;
    private CategoryService categoryService;

    public BookService(BookRepository bookRepository, AuthorService authorService, PublisherService publisherService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.categoryService = categoryService;
    }

    public Set<Book> getAllBooks() {
        Set<Book> books = new HashSet<>();
        bookRepository.findAll().iterator().forEachRemaining(books::add);

        return books;
    }

    public Book getBookById(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        return optionalBook.get();
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public void saveBook(Book book) {
        Author author = book.getAuthor();
        authorService.isAuthorExist(book, author);

        Publisher publisher = book.getPublisher();
        publisherService.isPublisherExist(book, publisher);

        bookRepository.save(book);
    }



    public Set<Book> getBooksByTitle(String title) {
        return bookRepository.findBooksWithPartOfNames(title);
    }

    public Set<Book> getBooksByCategory(String description) {
        Category category = categoryService.getCategoryByDescription(description);

        return category.getBooks();
    }

    public Long countAllBooks() {
        return bookRepository.countBooks();
    }
}
