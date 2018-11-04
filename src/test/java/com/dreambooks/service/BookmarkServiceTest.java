package com.dreambooks.service;

import com.dreambooks.model.Book;
import com.dreambooks.model.Bookmark;
import com.dreambooks.model.User;
import com.dreambooks.repository.BookmarkRepository;
import com.dreambooks.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class BookmarkServiceTest {

    @Mock
    private BookmarkRepository bookmarkRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private BookmarkService bookmarkService;

    private Bookmark bookmark;
    private User user;

    @BeforeEach
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldConnectBookmarkToUser() {
        user = new User();
        bookmark = new Bookmark();
        bookmarkService.connectBookmarkToUser(user, bookmark);

        verify(bookmarkRepository, times(1)).save(bookmark);
    }

    @Test
    public void shouldReturnBookmarkByUser() {
        bookmark = new Bookmark();
        user = new User();
        user.setEmail("test@gmail.com");
        bookmark.setUser(user);

        when(userRepository.findByEmail("test@gmail.com")).thenReturn(user);
        when(bookmarkRepository.findByUser(user)).thenReturn(bookmark);

        Bookmark bookmarkReturned = bookmarkService.getBookmarkByUser("test@gmail.com");

        assertEquals("test@gmail.com", bookmarkReturned.getUser().getEmail());
    }

    @Test
    public void shouldReturnBooksByBookmark() {
        bookmark = new Bookmark();
        Set<Book> books = new HashSet<>();
        books.add(new Book());
        bookmark.setBooks(books);

        assertEquals(1, bookmark.getBooks().size());
    }

    @Test
    public void shouldAddBookToBookmark() {
        bookmark = new Bookmark();
        Set<Book> books = new HashSet<>();
        Book book = new Book();
        books.add(book);
        bookmark.setBooks(books);
        when(bookmarkService.getBookmarkByUser("test@gmail.com")).thenReturn(bookmark);
        bookmarkService.addBookToBookmark(book, "test@gmail.com");

        verify(bookmarkRepository, times(1)).save(bookmark);
    }



}