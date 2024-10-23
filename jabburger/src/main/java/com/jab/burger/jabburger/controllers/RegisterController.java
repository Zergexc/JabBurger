package com.jab.burger.jabburger.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jab.burger.jabburger.services.UserService;
import com.jab.burger.jabburger.models.User;
import com.jab.burger.jabburger.Exceptions.UserAlreadyExistsException;

import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
@RequestMapping("/Registro")
public class RegisterController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public RegisterController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User());
        }
        return "registration";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("user") @Validated User user,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        try {
            userService.createUser(user);
            redirectAttributes.addFlashAttribute("message", "Registration successful!");
            return "redirect:/login";
        } catch (UserAlreadyExistsException e) {
            bindingResult.rejectValue("email", "error.user", "User with this email already exists.");
            return "registration";
        }
    }
}
