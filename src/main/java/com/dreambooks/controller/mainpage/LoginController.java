package com.dreambooks.controller.mainpage;

import com.dreambooks.service.BookService;
import com.dreambooks.utils.SearchObjects;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private BookService bookService;

    public LoginController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping(value = "/login")
    public String login(Model model) {
        model.addAttribute("searchObjects", new SearchObjects());
        model.addAttribute("book", bookService.getRandomBook());
        return "/mainpage/login";
    }


}
