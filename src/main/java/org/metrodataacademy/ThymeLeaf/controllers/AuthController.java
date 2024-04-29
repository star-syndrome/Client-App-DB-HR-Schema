package org.metrodataacademy.Thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
    
    @GetMapping(
        path = "/login"
    )
    public String loginForm() {
        return "auth/login";
    }
}