package com.dreambooks.service;

import com.dreambooks.model.Author;
import com.dreambooks.model.Book;
import com.dreambooks.repository.AuthorRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    private Author author;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnAllAuthors() {
        List<Author> authorsData = new LinkedList<>();
        authorsData.add(author);

        when(authorRepository.findAll()).thenReturn(authorsData);

        Set<Author> authors = authorService.getAllAuthors();

        assertEquals(1, authors.size());
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    public void shouldReturnAuthorByName() {
        author = new Author();
        author.setName("Author");

        when(authorRepository.findByName("Author")).thenReturn(author);

        assertEquals(author, authorService.findByName("Author"));
        verify(authorRepository, times(1)).findByName("Author");
    }

    @Test
    public void shouldReturnNullBecauseAuthorDoesNotExist() {
        when(authorRepository.findByName("Author")).thenReturn(null);

        assertNull(authorService.findByName("Author"));
    }

    @Test
    public void shouldSaveTheAuthorOnlyOnce() {

       authorRepository.save(author);
       verify(authorRepository, times(1)).save(author);
    }
}