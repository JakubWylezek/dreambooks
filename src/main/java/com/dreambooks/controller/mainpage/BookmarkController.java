package com.dreambooks.controller.mainpage;

import com.dreambooks.service.BookService;
import com.dreambooks.service.BookmarkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class BookmarkController {

    private BookmarkService bookmarkService;
    private BookService bookService;

    public BookmarkController(BookmarkService bookmarkService, BookService bookService) {
        this.bookmarkService = bookmarkService;
        this.bookService = bookService;
    }

    @GetMapping(value = "/bookmark/add/{id}")
    public String addBookToBookmark(@PathVariable String id, Principal principal) {
        bookmarkService.addBookToBookmark(
                bookService.getBookById(new Long(id)),
                principal.getName());

        return "redirect:/main";
    }

    @GetMapping(value = "/bookmark/books")
    public String getBooksFromBookmark(Model model, Principal principal) {
        model.addAttribute("books", bookmarkService.getBooksFromBookmark(principal.getName()));

        return "/mainpage/bookmark";
    }

    @GetMapping(value = "/bookmark/delete/{id}")
    public String deleteBookFrommBookmark(@PathVariable String id, Principal principal) {
        bookmarkService.deteleBookInBookmark(
                bookService.getBookById(new Long(id)),
                principal.getName());

        return "redirect:/bookmark/books";

    }
}
