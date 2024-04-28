package org.metrodataacademy.Thymeleaf.controllers.rest;

import java.util.List;

import org.metrodataacademy.Thymeleaf.models.dtos.request.CreateCountryRequest;
import org.metrodataacademy.Thymeleaf.models.dtos.request.UpdateCountryRequest;
import org.metrodataacademy.Thymeleaf.models.dtos.response.CountryResponse;
import org.metrodataacademy.Thymeleaf.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/country")
public class RestCountryController {

    @Autowired
    private CountryService countryService;
    
    @GetMapping(
        path = "/getAll",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<CountryResponse>> getAll() {
        return ResponseEntity.ok()
        .body(countryService.getAll());
    }

    @GetMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CountryResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok()
        .body(countryService.getById(id));
    }

    @PostMapping(
        path = "/create",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CountryResponse> create(@RequestBody CreateCountryRequest countryRequest) {
        return ResponseEntity.ok()
        .body(countryService.createCountry(countryRequest));
    }

    @PutMapping(
        path = "/update/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CountryResponse> update(@PathVariable Integer id, @RequestBody UpdateCountryRequest countryRequest) {
        return ResponseEntity.ok()
        .body(countryService.updateCountry(id, countryRequest));
    }

    @DeleteMapping(
        path = "/delete/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CountryResponse> delete(@PathVariable Integer id) {
        return ResponseEntity.ok()
        .body(countryService.deleteCountry(id));
    }
}