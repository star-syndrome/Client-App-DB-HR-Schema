package org.metrodataacademy.ThymeLeaf.controllers;

import org.metrodataacademy.ThymeLeaf.models.Region;
import org.metrodataacademy.ThymeLeaf.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/region")
public class RegionController {

    @Autowired
    private RegionService regionService;
    
    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String getAllRegion(Model model) {
        model.addAttribute("regions", regionService.getAll());
        return "region/dashboard";
    }

    @GetMapping(
        path = "/create",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String createView(Region region) {
        return "region/add";
    }

    @PostMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String createRegion(Region region) {
        regionService.createRegion(region);
        return "redirect:/region";
    }

    @GetMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String getById(@PathVariable Integer id, Model model) {
        model.addAttribute("region", regionService.getById(id));
        return "region/details";
    }

    @GetMapping(
        path = "/update/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String updateView(@PathVariable Integer id, Model model) {
        model.addAttribute("region", regionService.getById(id));
        return "region/update";
    }

    @PostMapping(
        path = "/update",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String updateRegion(Region region) {
        regionService.updateRegion(region.getId(), region);
        return "redirect:/region";
    }   

    @GetMapping(
        path = "/delete/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String deleteRegion(@PathVariable Integer id) {
        regionService.deleteRegion(id);
        return "redirect:/region";
    }
}