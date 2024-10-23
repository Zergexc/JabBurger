package com.jab.burger.jabburger.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jab.burger.jabburger.services.UserService;
import com.jab.burger.jabburger.models.User;
import com.jab.burger.jabburger.Exceptions.UserAlreadyExistsException;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/Registro")
public class RegisterController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User());
        }
        return "Registro";
    }

    @PostMapping
    public String registerUser(@ModelAttribute("user") @Valid User user, BindingResult result, RedirectAttributes redirectAttributes) {
        logger.info("Intentando registrar usuario: {}", user.getEmail());
        
        if (result.hasErrors()) {
            logger.warn("Se produjeron errores de validación: {}", result.getAllErrors());
            return "Registro";
        }
        
        try {
            userService.createUser(user);
            logger.info("Usuario registrado exitosamente: {}", user.getEmail());
            redirectAttributes.addFlashAttribute("successMessage", "Registro exitoso. Por favor, inicia sesión.");
            return "redirect:/login";
        } catch (UserAlreadyExistsException e) {
            logger.warn("El usuario ya existe: {}", user.getEmail());
            result.rejectValue("email", "error.user", "Ya existe un usuario con este correo electrónico.");
            return "Registro";
        } catch (Exception e) {
            logger.error("Error inesperado durante el registro de usuario", e);
            result.rejectValue("email", "error.user", "Ocurrió un error inesperado. Por favor, inténtalo de nuevo.");
            return "Registro";
        }
    }
}
