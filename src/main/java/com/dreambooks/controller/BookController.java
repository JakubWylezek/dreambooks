package com.dreambooks.controller;

import com.dreambooks.model.Author;
import com.dreambooks.model.Book;
import com.dreambooks.model.Category;
import com.dreambooks.model.Publisher;
import com.dreambooks.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

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

    @RequestMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable String id) {
        bookService.deleteBook(new Long(id));
        return "redirect:/books";
    }

    @RequestMapping("/books/update/{id}")
    public String updateBook(@PathVariable String id, Model model) {
        model.addAttribute("book", bookService.getBookById(new Long(id)));

        return "books/edit";
    }

    @RequestMapping("/books/add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "books/add";
    }

    @RequestMapping("/books/save")
    public String saveBook(@ModelAttribute Book book) {
        bookService.saveBook(book);
        return "redirect:/books";
    }
}
