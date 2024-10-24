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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controlador para manejar el registro de nuevos usuarios.
 */
@Controller
@RequestMapping("/Registro")
public class RegisterController {

    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);
    private final UserService userService;

    /**
     * Constructor para RegisterController.
     *
     * @param userService el servicio de usuario a utilizar
     */
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Muestra el formulario de registro.
     *
     * @param model el modelo para añadir atributos
     * @return el nombre de la vista "Registro"
     */
    @GetMapping
    public String showRegistrationForm(Model model) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User());
        }
        return "Registro";
    }

    /**
     * Maneja la solicitud POST para registrar un nuevo usuario.
     *
     * @param user el usuario a registrar
     * @param bindingResult el resultado de la validación
     * @param redirectAttributes para añadir atributos de redirección
     * @return redirecciona a la página de inicio de sesión o muestra el formulario de registro si hay errores
     */
    @PostMapping
    public String registerUser(@ModelAttribute @Validated User user,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "Registro";
        }

        try {
            userService.createUser(user);
            logger.info("Usuario registrado correctamente: {}", user.getEmail());
            redirectAttributes.addFlashAttribute("message", "Registro exitoso. Por favor, inicia sesión.");
            return "redirect:/login";
        } catch (UserAlreadyExistsException e) {
            bindingResult.rejectValue("email", "error.user", "Ya existe un usuario con este correo electrónico.");
            return "Registro";
        }
    }
}
