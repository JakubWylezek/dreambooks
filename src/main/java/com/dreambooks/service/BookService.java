package com.dreambooks.service;

import com.dreambooks.model.Author;
import com.dreambooks.model.Book;
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

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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

    public Book addBook(Book book) {
        Author author = book.getAuthor();
        authorService.isAuthorExist(book, author);

        Publisher publisher = book.getPublisher();
        publisherService.isPublisherExist(book, publisher);

        return book;
    }

    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    public Set<Book> getBooksByAuthor(String nameOfAuthor) {
        Author author = authorService.findByName(nameOfAuthor);
        return author.getBooks();
    }

    public Set<Book> getBooksByPublisher(String nameOfPublisher) {
        Publisher publisher = publisherService.findByName(nameOfPublisher);
        return publisher.getBooks();
    }
}
