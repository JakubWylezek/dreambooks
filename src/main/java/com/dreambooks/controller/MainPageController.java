package com.dreambooks.controller;

import com.dreambooks.service.BookService;
import com.dreambooks.service.CategoryService;
import com.dreambooks.utils.SearchObjects;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainPageController {

    private BookService bookService;
    private CategoryService categoryService;

    public MainPageController(BookService bookService, CategoryService categoryService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @RequestMapping("/main")
    public String getMainPage(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("searchObjects", new SearchObjects());

        return "/mainpage/index";
    }


}
