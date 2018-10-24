package com.dreambooks.controller;

import com.dreambooks.service.BookService;
import com.dreambooks.utils.SearchObjects;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainPageController {

    private BookService bookService;

    public MainPageController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/main")
    public String getMainPage(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("searchObjects", new SearchObjects());

        return "/mainpage/index";
    }


}
