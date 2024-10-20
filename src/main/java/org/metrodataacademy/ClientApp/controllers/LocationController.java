package org.metrodataacademy.ClientApp.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/location")
public class LocationController {

    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String getAll(Model model) {
        model.addAttribute("isActive", "location");
        return "pages/location";
    }
}