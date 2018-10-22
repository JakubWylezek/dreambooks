package com.dreambooks.controller;

import com.dreambooks.model.Book;
import com.dreambooks.service.AuthorService;
import com.dreambooks.service.BookService;
import com.dreambooks.service.PublisherService;
import com.dreambooks.utils.SearchObjects;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        model.addAttribute("countBooks", bookService.countAllBooks());

        return "/adminpanel/index";
    }


}
