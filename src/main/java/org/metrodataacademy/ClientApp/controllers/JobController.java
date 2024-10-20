package org.metrodataacademy.ClientApp.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/job")
public class JobController {

    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String getAll(Model model) {
        model.addAttribute("isActive", "job");
        return "pages/job";
    }
}
