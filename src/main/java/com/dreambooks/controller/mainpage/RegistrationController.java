package com.dreambooks.controller.mainpage;

import com.dreambooks.model.User;
import com.dreambooks.service.BookService;
import com.dreambooks.service.UserService;
import com.dreambooks.utils.SearchObjects;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private BookService bookService;
    private UserService userService;

    public RegistrationController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping(value = "/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("searchObjects", new SearchObjects());
        model.addAttribute("book", bookService.getRandomBook());
        return "/mainpage/registration";
    }

    @PostMapping(value = "/registration")
    public String createNewUser(@Valid User user, BindingResult bindingResult, Model model) {
        User userExists = userService.findUserByEmail(user.getEmail());

        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("searchObjects", new SearchObjects());
            return "/mainpage/registration";
        }
        else {
            userService.saveUser(user);
            model.addAttribute("successMessage", "User has been registered successfully");
            model.addAttribute("user", new User());
            model.addAttribute("searchObjects", new SearchObjects());
        }
        return "/mainpage/registration";
    }
}
