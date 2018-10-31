package com.dreambooks.controller.mainpage;

import com.dreambooks.service.BookService;
import com.dreambooks.service.CategoryService;
import com.dreambooks.utils.SearchObjects;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class BookMainPageController {

    private BookService bookService;
    private CategoryService categoryService;

    public BookMainPageController(BookService bookService, CategoryService categoryService) {
        this.bookService = bookService;
        this.categoryService = categoryService;
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
