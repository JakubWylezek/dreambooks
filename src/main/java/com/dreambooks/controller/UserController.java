package com.dreambooks.controller;

import com.dreambooks.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/adminpanel/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());

        return "/adminpanel/users";
    }
}
