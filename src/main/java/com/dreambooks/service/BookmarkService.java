package com.dreambooks.service;

import com.dreambooks.model.Book;
import com.dreambooks.model.Bookmark;
import com.dreambooks.model.User;
import com.dreambooks.repository.BookmarkRepository;
import com.dreambooks.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class BookmarkService {

    private BookmarkRepository bookmarkRepository;
    private UserRepository userRepository;

    public BookmarkService(BookmarkRepository bookmarkRepository, UserRepository userRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.userRepository = userRepository;
    }


    public void connectBookmarkToUser(User user, Bookmark bookmark) {
        bookmark.setUser(user);
        bookmarkRepository.save(bookmark);
    }

    public Bookmark getBookmarkByUser(String email) {
        User user = userRepository.findByEmail(email);
        return bookmarkRepository.findByUser(user);
    }

    public void addBookToBookmark(Book book, String email) {
        Bookmark bookmark = getBookmarkByUser(email);
        bookmark.getBooks().add(book);

        bookmarkRepository.save(bookmark);
    }

    public Set<Book> getBooksFromBookmark(String email) {
        Bookmark bookmark = getBookmarkByUser(email);

        return bookmark.getBooks();
    }

    public void deteleBookInBookmark(Book book, String email) {
        Bookmark bookmark = getBookmarkByUser(email);
        bookmark.getBooks().remove(book);

        bookmarkRepository.save(bookmark);
    }




}
