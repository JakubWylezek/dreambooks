package com.dreambooks.service;

import com.dreambooks.model.Author;
import com.dreambooks.model.Book;
import com.dreambooks.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author findByName(String name) {
        return authorRepository.findByName(name);
    }

    public void isAuthorExist(Book book, Author author) {
        Author new_author = authorRepository.findByName(author.getName());

        if(new_author == null)
            authorRepository.save(author);
        else
            book.setAuthor(new_author);
    }
}
