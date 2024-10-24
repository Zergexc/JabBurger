package com.jab.burger.jabburger.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;

/**
 * Controlador para manejar las solicitudes a la página de inicio.
 */
@Controller
public class HomeController {

    /**
     * Maneja las solicitudes GET para la página de inicio.
     *
     * @param model el modelo para pasar atributos a la vista
     * @param authentication la autenticación del usuario actual
     * @return el nombre de la vista "Inicio"
     */
    @GetMapping({"", "/", "/Inicio"})
    public String inicio(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            model.addAttribute("username", authentication.getName());
        }
        return "Inicio";
    }
}
