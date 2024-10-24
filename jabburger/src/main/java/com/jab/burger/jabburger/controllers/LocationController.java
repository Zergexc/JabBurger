package com.jab.burger.jabburger.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador para manejar las solicitudes a la página de ubicación.
 */
@Controller
public class LocationController {

    /**
     * Maneja las solicitudes GET para la página de ubicación.
     *
     * @return el nombre de la vista "Ubiquenos"
     */
    @GetMapping("/ubiquenos")
    public String location() {
        return "Ubiquenos";
    }
}
