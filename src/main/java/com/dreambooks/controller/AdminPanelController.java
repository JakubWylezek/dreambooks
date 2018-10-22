package com.dreambooks.controller;

import com.dreambooks.model.Book;
import com.dreambooks.service.AuthorService;
import com.dreambooks.service.BookService;
import com.dreambooks.service.PublisherService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminPanelController {

    private BookService bookService;
    private AuthorService authorService;
    private PublisherService publisherService;

    public AdminPanelController(BookService bookService, AuthorService authorService, PublisherService publisherService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.publisherService = publisherService;
    }

    /*
    Change it after to getOrders
     */
    @RequestMapping("/adminpanel")
    public String getAdminPanel(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("publishers", publisherService.getAllPublishers());

        return "/adminpanel/index";
    }

    @RequestMapping("/adminpanel/books")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());

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
}
