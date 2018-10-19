package com.dreambooks.controller;

import com.dreambooks.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminPanelController {

    private BookService bookService;

    public AdminPanelController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/adminpanel/index")
    public String getAllOrders() {
        return "adminpanel/index";
    }

    @RequestMapping("/adminpanel/books")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());

        return "adminpanel/books";
    }
}
