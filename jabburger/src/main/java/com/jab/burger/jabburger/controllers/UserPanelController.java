package com.jab.burger.jabburger.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jab.burger.jabburger.models.User;

/**
 * Controlador para manejar las solicitudes al panel de usuario.
 */
@Controller
public class UserPanelController {

    /**
     * Muestra el panel de usuario.
     *
     * @param user el usuario autenticado
     * @param model el modelo para añadir atributos
     * @return el nombre de la vista "UserPanel"
     */
    @GetMapping("/PanelUsuario")
    public String userPanel(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "UserPanel";
    }
}
