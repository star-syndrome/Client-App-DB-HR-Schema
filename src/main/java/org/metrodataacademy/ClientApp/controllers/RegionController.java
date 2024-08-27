package org.metrodataacademy.ClientApp.controllers;

import org.metrodataacademy.ClientApp.models.dtos.request.CreateRegionRequest;
import org.metrodataacademy.ClientApp.models.dtos.request.UpdateRegionRequest;
import org.metrodataacademy.ClientApp.models.entities.Region;
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
@RequestMapping("/region")
public class RegionController {

    @Autowired
    private RegionService regionService;
    
    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String getAllRegion(Model model) {
        model.addAttribute("isActive", "region");
        model.addAttribute("regions", regionService.getAll());
        return "region/dashboard";
    }

    @GetMapping(
        path = "/create",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String createView(CreateRegionRequest regionRequest, Model model) {
        model.addAttribute("isActive", "region");
        return "region/add";
    }

    @PostMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String createRegion(CreateRegionRequest regionRequest) {
        regionService.createRegion(regionRequest);
        return "redirect:/region";
    }

    @GetMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String getById(@PathVariable Integer id, Model model) {
        model.addAttribute("isActive", "region");
        model.addAttribute("region", regionService.getById(id));
        return "region/details";
    }

    @GetMapping(
        path = "/update/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String updateView(@PathVariable Integer id, Model model, Region region) {
        model.addAttribute("isActive", "region");
        model.addAttribute("region", regionService.getById(id));
        return "region/update";
    }

    @PutMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String updateRegion(@PathVariable Integer id, UpdateRegionRequest regionRequest) {
        regionService.updateRegion(id, regionRequest);
        return "redirect:/region";
    }   

    @DeleteMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String deleteRegion(@PathVariable Integer id) {
        regionService.deleteRegion(id);
        return "redirect:/region";
    }
}