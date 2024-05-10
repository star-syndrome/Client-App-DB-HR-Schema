package org.metrodataacademy.Thymeleaf.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String getUser(Model model) {
        model.addAttribute("isActive", "profile");
        return "profile/dashboard";
    }   
}