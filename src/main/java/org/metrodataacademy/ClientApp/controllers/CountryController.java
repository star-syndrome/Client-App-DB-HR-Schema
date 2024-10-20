package org.metrodataacademy.ClientApp.controllers;

import org.metrodataacademy.ClientApp.models.dtos.request.CreateCountryRequest;
import org.metrodataacademy.ClientApp.models.dtos.request.UpdateCountryRequest;
import org.metrodataacademy.ClientApp.services.CountryService;
import org.metrodataacademy.ClientApp.services.RegionService;
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
        model.addAttribute("isActive", "country");
        model.addAttribute("countries", countryService.getAll());
        return "pages/country";
    }

    @GetMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String getById(@PathVariable Integer id, Model model) {
        model.addAttribute("isActive", "country");
        model.addAttribute("country", countryService.getById(id));
        return "country/details";
    }

    @GetMapping(
        path = "/create",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String createView(CreateCountryRequest countryRequest, Model model) {
        model.addAttribute("isActive", "country");
        model.addAttribute("regions", regionService.getAll());
        return "country/add";
    }

    @PostMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String createCountry(CreateCountryRequest countryRequest) {
        countryService.createCountry(countryRequest);
        return "redirect:/country";
    }

    @GetMapping(
        path = "/update/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String updateView(@PathVariable Integer id, Model model, UpdateCountryRequest countryRequest) {
        model.addAttribute("isActive", "country");
        model.addAttribute("country", countryService.getById(id));
        return "country/update";
    }

    @PutMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String updateCountry(@PathVariable Integer id, UpdateCountryRequest countryRequest) {
        countryService.updateCountry(id, countryRequest);
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