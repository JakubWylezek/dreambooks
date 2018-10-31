package com.dreambooks.controller;

import com.dreambooks.model.Author;
import com.dreambooks.model.Book;
import com.dreambooks.model.Publisher;
import com.dreambooks.service.AuthorService;
import com.dreambooks.service.BookService;
import com.dreambooks.service.CategoryService;
import com.dreambooks.service.PublisherService;
import com.dreambooks.utils.SearchObjects;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class BookController {

    private BookService bookService;
    private AuthorService authorService;
    private PublisherService publisherService;
    private CategoryService categoryService;

    public BookController(BookService bookService, AuthorService authorService, PublisherService publisherService, CategoryService categoryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/adminpanel/books")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("searchObjects", new SearchObjects());

        return "/adminpanel/books";
    }

    @PostMapping(value = "/adminpanel/books/search")
    public String setSearch(@ModelAttribute SearchObjects searchObjects, Model model) {
        model.addAttribute("books", bookService.getBooksByTitle(searchObjects.getSearchDescription()));

        return "/adminpanel/books";
    }

    @GetMapping(value = "/adminpanel/book/{id}")
    public String getBookById(@PathVariable String id, Model model) {
        model.addAttribute("book", bookService.getBookById(new Long(id)));
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("publishers", publisherService.getAllPublishers());
        model.addAttribute("categories", categoryService.getAllCategories());

        return "/adminpanel/bookdetails";
    }

    @PostMapping(value = "/adminpanel/book/save")
    public String saveBook(@Valid Book book, BindingResult bindingResult) {

        if(bindingResult.hasErrors())
            return "redirect:/adminpanel/book/" + book.getId();

        bookService.saveBook(book);
        return "redirect:/adminpanel/books";
    }

    @GetMapping(value = "/adminpanel/book/delete/{id}")
    public String deleteBook(@PathVariable String id) {
        bookService.deleteBook(new Long(id));

        return "redirect:/adminpanel/books";
    }


    @GetMapping(value = "/main/book/{id}")
    public String getMainPageBookById(@PathVariable String id, Model model, Principal principal) {
        model.addAttribute("book", bookService.getBookById(new Long(id)));
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("searchObjects", new SearchObjects());

        if(principal == null)
            model.addAttribute("user_email", new String(" "));
        else
            model.addAttribute("user_email", principal.getName());

        return "/mainpage/bookdetails";
    }

    @PostMapping(value = "/main/books/search")
    public String setMainPageSearch(@ModelAttribute SearchObjects searchObjects, Model model, Principal principal) {
        model.addAttribute("books", bookService.getBooksByTitle(searchObjects.getSearchDescription()));
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("searchObjects", new SearchObjects());

        if(principal == null)
            model.addAttribute("user_email", new String(" "));
        else
            model.addAttribute("user_email", principal.getName());

        return "/mainpage/index";
    }

    @GetMapping(value = "/main/category/{description}")
    public String getMainPageBooksByCategory(@PathVariable String description, Model model, Principal principal) {
        model.addAttribute("books", bookService.getBooksByCategory(description));
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("searchObjects", new SearchObjects());

        if(principal == null)
            model.addAttribute("user_email", new String(" "));
        else
            model.addAttribute("user_email", principal.getName());

        return "/mainpage/index";
    }
}