package com.example.sessionsmultiplelayers.controllers;

import com.example.sessionsmultiplelayers.models.User;
import com.example.sessionsmultiplelayers.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/create/user")
    public String createUser(Model model){
        User newUser = new User();
        model.addAttribute("newUser", newUser);
        return "createUser";

    }

    @PostMapping(path = "/create/user")
    public String createUserSubmit(@ModelAttribute("newUser") User newUser) {
        userRepository.createUser(newUser);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        model.addAttribute("loginAttempt", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpSession session, Model model, User loginAttempt)
    {
        User user = userRepository.getUser(loginAttempt.getUserName(), loginAttempt.getPassword());
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/projects";
        }
        // wrong credentials
        model.addAttribute("wrongCredentials", true);
        return "redirect:/login";
    }
}
