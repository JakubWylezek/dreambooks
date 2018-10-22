package com.dreambooks.controller;

import com.dreambooks.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/adminpanel/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());

        return "/adminpanel/users";
    }
}