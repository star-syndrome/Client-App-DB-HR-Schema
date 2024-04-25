package org.metrodataacademy.Thymeleaf.controllers;

import org.metrodataacademy.Thymeleaf.models.Country;
import org.metrodataacademy.Thymeleaf.services.CountryService;
import org.metrodataacademy.Thymeleaf.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/country")
public class CountryController {
    
    @Autowired
    private CountryService countryService;

    @Autowired
    private RegionService regionService;

    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String getAll(Model model) {
        model.addAttribute("countries", countryService.getAll());
        return "country/dashboard";
    }

    @GetMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String getById(@PathVariable Integer id, Model model) {
        model.addAttribute("country", countryService.getById(id));
        return "country/details";
    }

    @GetMapping(
        path = "/create",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String createView(Country country, Model model) {
        model.addAttribute("regions", regionService.getAll());
        return "country/add";
    }

    @PostMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String createCountry(Country country) {
        countryService.createCountry(country);
        return "redirect:/country";
    }

    @GetMapping(
        path = "/update/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String updateView(@PathVariable Integer id, Model model, Country country) {
        model.addAttribute("country", countryService.getById(id));
        return "country/update";
    }

    @PutMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String updateCountry(@PathVariable Integer id, Country country) {
        countryService.updateCountry(id, country);
        return "redirect:/country";
    }
    
    @DeleteMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String deleteCountry(@PathVariable Integer id) {
        countryService.deleteCountry(id);
        return "redirect:/country";
    }
}