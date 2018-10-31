package com.dreambooks.controller;

import com.dreambooks.model.User;
import com.dreambooks.service.RoleService;
import com.dreambooks.service.UserService;
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
public class UserController {

    private UserService userService;
    private RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/adminpanel/users")
    public String getAllBooks(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("searchObjects", new SearchObjects());

        return "/adminpanel/users";
    }

    @GetMapping(value = "/adminpanel/user/{id}")
    public String getBookById(@PathVariable String id, Model model) {
        model.addAttribute("user", userService.getUserById(new Long(id)));
        model.addAttribute("roles", roleService.getAllRoles());

        return "/adminpanel/userdetails";
    }

    @GetMapping(value = "/adminpanel/user/delete/{id}")
    public String deleteBook(@PathVariable String id) {
        userService.deleteUser(new Long(id));

        return "redirect:/adminpanel/users";
    }

    @PostMapping(value = "/adminpanel/user/save")
    public String saveBook(@Valid User user, BindingResult bindingResult) {

        if(bindingResult.hasErrors())
            return "redirect:/adminpanel/user/" + user.getId();

        userService.updateUser(user);
        return "redirect:/adminpanel/users";
    }
}
