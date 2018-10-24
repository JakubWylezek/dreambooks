package com.dreambooks.controller;

import com.dreambooks.model.Book;
import com.dreambooks.service.AuthorService;
import com.dreambooks.service.BookService;
import com.dreambooks.service.PublisherService;
import com.dreambooks.utils.SearchObjects;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {

    private BookService bookService;
    private AuthorService authorService;
    private PublisherService publisherService;

    public BookController(BookService bookService, AuthorService authorService, PublisherService publisherService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.publisherService = publisherService;
    }

    @RequestMapping("/adminpanel/books")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("searchObjects", new SearchObjects());

        return "/adminpanel/books";
    }

    @RequestMapping("/adminpanel/books/search")
    public String setSearch(@ModelAttribute SearchObjects searchObjects, Model model) {
        model.addAttribute("books", bookService.getBooksByTitle(searchObjects.getSearchDescription()));

        return "/adminpanel/books";
    }

    @RequestMapping("/adminpanel/book/{id}")
    public String getBookById(@PathVariable String id, Model model) {
        model.addAttribute("book", bookService.getBookById(new Long(id)));
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("publishers", publisherService.getAllPublishers());

        return "/adminpanel/bookdetails";
    }

    @RequestMapping("/adminpanel/book/save")
    public String saveBook(@ModelAttribute Book book) {
        bookService.saveBook(book);

        return "redirect:/adminpanel/books";
    }

    @RequestMapping("/adminpanel/book/delete/{id}")
    public String deleteBook(@PathVariable String id) {
        bookService.deleteBook(new Long(id));

        return "redirect:/adminpanel/books";
    }


    @RequestMapping("/main/book/{id}")
    public String getMainPageBookById(@PathVariable String id, Model model) {
        model.addAttribute("book", bookService.getBookById(new Long(id)));

        return "/mainpage/bookdetails";
    }

    @RequestMapping("/main/books/search")
    public String setMainPageSearch(@ModelAttribute SearchObjects searchObjects, Model model) {
        model.addAttribute("books", bookService.getBooksByTitle(searchObjects.getSearchDescription()));

        return "/mainpage/index";
    }
}