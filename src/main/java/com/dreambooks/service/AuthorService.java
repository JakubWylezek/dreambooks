package com.dreambooks.service;

import com.dreambooks.model.Author;
import com.dreambooks.model.Book;
import com.dreambooks.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Set<Author> getAllAuthors() {
        Set<Author> authors = new HashSet<>();
        authorRepository.findAll().iterator().forEachRemaining(authors::add);

        return authors;
    }
    public Author findByName(String name) {
        return authorRepository.findByName(name);
    }

    public void isAuthorExist(Book book, Author author) {
        Author new_author = authorRepository.findByName(author.getName());

        if(new_author == null)
            authorRepository.save(author);

        book.setAuthor(new_author);
    }
}
