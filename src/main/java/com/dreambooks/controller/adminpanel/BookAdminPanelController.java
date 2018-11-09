package com.dreambooks.controller.adminpanel;

import com.dreambooks.model.Book;
import com.dreambooks.service.AuthorService;
import com.dreambooks.service.BookService;
import com.dreambooks.service.CategoryService;
import com.dreambooks.service.PublisherService;
import com.dreambooks.utils.SearchObjects;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class BookAdminPanelController {

    private BookService bookService;
    private AuthorService authorService;
    private PublisherService publisherService;
    private CategoryService categoryService;

    public BookAdminPanelController(BookService bookService, AuthorService authorService, PublisherService publisherService, CategoryService categoryService) {
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
    public String getSearchObject(@ModelAttribute SearchObjects searchObjects, Model model) {
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

    @PostMapping(value = "/adminpanel/book/update")
    public String updateBook(@Valid Book book, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("authors", authorService.getAllAuthors());
            model.addAttribute("publishers", publisherService.getAllPublishers());
            model.addAttribute("categories", categoryService.getAllCategories());

            return "/adminpanel/bookdetails";
        }

        bookService.saveBook(book);
        return "redirect:/adminpanel/books";
    }

    @PostMapping(value = "/adminpanel/book/save")
    public String saveBook(@Valid Book book, BindingResult bindingResult) {

        if(bindingResult.hasErrors())
            return "redirect:/adminpanel";

        bookService.saveBook(book);
        return "redirect:/adminpanel/books";
    }

    @GetMapping(value = "/adminpanel/book/delete/{id}")
    public String deleteBook(@PathVariable String id) {
        bookService.deleteBook(new Long(id));

        return "redirect:/adminpanel/books";
    }
}
