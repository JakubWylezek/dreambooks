package com.dreambooks.controller;

import com.dreambooks.model.Category;
import com.dreambooks.service.BookService;
import com.dreambooks.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {

    private CategoryService categoryService;
    private BookService bookService;

    public CategoryController(CategoryService categoryService, BookService bookService) {
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @GetMapping(value = "/adminpanel/categories")
    public String getAllCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "/adminpanel/categories";
    }

    @GetMapping(value = "/adminpanel/category/{id}")
    public String getBookById(@PathVariable String id, Model model) {
        model.addAttribute("category", categoryService.getCategoryById(new Long(id)));
        model.addAttribute("books", bookService.getBooksByCategory(categoryService.getCategoryById(new Long(id)).getDescription()));
        return "/adminpanel/categorydetails";
    }

    @GetMapping(value = "/adminpanel/category/delete/{id}")
    public String deleteBook(@PathVariable String id) {
        categoryService.deleteCategory(new Long(id));

        return "redirect:/adminpanel/categories";
    }

    @PostMapping(value = "/adminpanel/category/save")
    public String saveBook(@ModelAttribute Category category) {
        categoryService.saveCategory(category);

        return "redirect:/adminpanel/categories";
    }
}
