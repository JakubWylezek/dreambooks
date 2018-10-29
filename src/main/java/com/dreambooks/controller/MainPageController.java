package com.dreambooks.controller;

import com.dreambooks.service.BookService;
import com.dreambooks.service.CategoryService;
import com.dreambooks.utils.SearchObjects;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


@Controller
public class MainPageController {

    private BookService bookService;
    private CategoryService categoryService;

    public MainPageController(BookService bookService, CategoryService categoryService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @GetMapping( value = "/main")
    public String getMainPage(Model model, Principal principal) {
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("searchObjects", new SearchObjects());
        if(principal == null)
            model.addAttribute("user_email", new String(" "));
        else
            model.addAttribute("user_email", principal.getName());

        return "/mainpage/index";
    }


}
