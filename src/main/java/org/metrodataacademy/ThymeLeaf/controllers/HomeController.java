package org.metrodataacademy.Thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping
    public String home(Model model) {
        model.addAttribute("isActive", "home");
        model.addAttribute("name", "Star Syndrome");
        return "index";
    }
}