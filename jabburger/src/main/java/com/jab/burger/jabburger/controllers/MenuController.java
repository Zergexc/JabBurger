

package com.jab.burger.jabburger.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    @GetMapping("/verMenu")
    public String loginForm() {
        return "Menu";
    }

    @GetMapping("/verPerzonalizarHamburgueza")
    public String verPerzonalizarHamburgueza(){
        return "PersonalizarH";
    }

    @GetMapping("/verUbiquenos")
    public String verUbiquenos(){
        return "Ubiquenos";
    }

    @GetMapping("/verNosotros")
    public String verNosotros(){
        return "Nosotros";
    }

    @GetMapping("/verCarrito")
    public String verCarrito(){
        return "MetodoPago";
    }
}