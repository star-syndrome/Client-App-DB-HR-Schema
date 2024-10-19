package org.metrodataacademy.ClientApp.controllers.rest;

import java.util.List;

import org.metrodataacademy.ClientApp.models.dtos.request.CreateLocationRequest;
import org.metrodataacademy.ClientApp.models.dtos.request.UpdateLocationRequest;
import org.metrodataacademy.ClientApp.models.dtos.response.LocationResponse;
import org.metrodataacademy.ClientApp.services.LocationService;
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
@RequestMapping(path = "/api/location")
public class RestLocationController {
    
    @Autowired
    private LocationService locationService;
    
    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<LocationResponse>> getAll() {
        return ResponseEntity.ok()
        .body(locationService.getAll());
    }

    @GetMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LocationResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok()
        .body(locationService.getById(id));
    }

    @PostMapping(
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LocationResponse> create(@RequestBody CreateLocationRequest locationRequest) {
        return ResponseEntity.ok()
        .body(locationService.createLocation(locationRequest));
    }

    @PutMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LocationResponse> update(@PathVariable Integer id, @RequestBody UpdateLocationRequest locationRequest) {
        return ResponseEntity.ok()
        .body(locationService.updateLocation(id, locationRequest));
    }

    @DeleteMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LocationResponse> delete(@PathVariable Integer id) {
        return ResponseEntity.ok()
        .body(locationService.deleteLocation(id));
    }
}