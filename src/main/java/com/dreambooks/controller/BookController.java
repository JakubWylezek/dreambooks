package com.dreambooks.controller;

import com.dreambooks.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/books")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());

        return "/books/index";
    }

    @RequestMapping("/books/show/{id}")
    public String getBook(@PathVariable String id, Model model) {
        model.addAttribute("book", bookService.getBookById(new Long(id)));

        return "books/show";
    }
}
