package com.dreambooks.controller.adminpanel;

import com.dreambooks.model.Book;
import com.dreambooks.repository.UserRepository;
import com.dreambooks.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPanelController {

    private BookService bookService;
    private AuthorService authorService;
    private PublisherService publisherService;
    private CategoryService categoryService;
    private UserRepository userRepository;

    public AdminPanelController(BookService bookService, AuthorService authorService, PublisherService publisherService, CategoryService categoryService, UserRepository userRepository) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.categoryService = categoryService;
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/adminpanel")
    public String getAdminPanel(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("publishers", publisherService.getAllPublishers());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("countBooks", bookService.countAllBooks());
        model.addAttribute("countUsers", userRepository.countUsers());
        model.addAttribute("users", userRepository.getMaxFiveNewUsers());
        model.addAttribute("countCategories", categoryService.countCategories());
        return "/adminpanel/index";
    }


}
