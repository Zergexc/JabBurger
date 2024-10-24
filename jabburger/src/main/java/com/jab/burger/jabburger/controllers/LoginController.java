package com.jab.burger.jabburger.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controlador para manejar las solicitudes relacionadas con el inicio de sesión.
 */
@Controller
public class LoginController {

    /**
     * Muestra el formulario de inicio de sesión.
     *
     * @return el nombre de la vista "login"
     */
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    /**
     * Muestra el formulario de registro.
     *
     * @return el nombre de la vista "registro"
     */
    @GetMapping("/registro")
    public String registroForm() {
        return "registro";
    }

    /**
     * Muestra un mensaje de error en el formulario de inicio de sesión.
     *
     * @param model el modelo para añadir atributos
     * @return el nombre de la vista "login"
     */
    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("error", "Credenciales incorrectas. Por favor, inténtalo de nuevo.");
        return "login";
    }

    /**
     * Maneja la solicitud de cierre de sesión.
     *
     * @param request la solicitud HTTP
     * @param response la respuesta HTTP
     * @return redirecciona a la página de inicio de sesión con un parámetro de cierre de sesión
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    /**
     * Redirecciona a la página de inicio.
     *
     * @return el nombre de la vista "Inicio"
     */
    @GetMapping("/inicio")
    public String inicio() {
        return "Inicio";
    }

    /**
     * Muestra la página de método de pago.
     *
     * @return el nombre de la vista "Pago"
     */
    @GetMapping("/MetodoPago")
    public String pago() {
        return "Pago";
    }
}
