package com.dreambooks.controller;

import com.dreambooks.model.Book;
import com.dreambooks.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminPanelController {

    private BookService bookService;

    public AdminPanelController(BookService bookService) {
        this.bookService = bookService;
    }

    /*
    Change it after to getOrders
     */
    @RequestMapping("/adminpanel")
    public String getAdminPanel() {
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
