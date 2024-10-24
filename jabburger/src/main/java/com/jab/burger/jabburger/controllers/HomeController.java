package com.jab.burger.jabburger.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

   
    @PostMapping("/suscripcionNoticias")
    public ResponseEntity<String> suscripcion(@RequestParam String email) {
        // Aquí podrías agregar lógica para almacenar el correo en una base de datos, etc.
        return ResponseEntity.ok("¡Gracias por suscribirte a nuestras noticias!");
    }

   
    @GetMapping("/home")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("¡Bienvenido a nuestra página de inicio!");
    }
}
