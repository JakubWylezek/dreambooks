package com.dreambooks.controller;

import com.dreambooks.model.Book;
import com.dreambooks.service.AuthorService;
import com.dreambooks.service.BookService;
import com.dreambooks.service.PublisherService;
import com.dreambooks.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminPanelController {

    private BookService bookService;
    private AuthorService authorService;
    private PublisherService publisherService;
    private UserService userService;

    public AdminPanelController(BookService bookService, AuthorService authorService, PublisherService publisherService, UserService userService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.userService = userService;
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
        model.addAttribute("countUsers", userService.countAllUsers());

        return "/adminpanel/index";
    }


}
