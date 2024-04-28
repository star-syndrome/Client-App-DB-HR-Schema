package org.metrodataacademy.Thymeleaf.controllers.rest;

import java.util.List;

import org.metrodataacademy.Thymeleaf.models.dtos.request.CreateRegionRequest;
import org.metrodataacademy.Thymeleaf.models.dtos.request.UpdateRegionRequest;
import org.metrodataacademy.Thymeleaf.models.dtos.response.RegionResponse;
import org.metrodataacademy.Thymeleaf.services.RegionService;
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
@RequestMapping(path = "/api/region")
public class RestRegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping(
        path = "/getAll",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<RegionResponse>> getAll() {
        return ResponseEntity.ok()
        .body(regionService.getAll());
    }
    
    @GetMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RegionResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok()
        .body(regionService.getById(id));
    }

    @PostMapping(
        path = "/create",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RegionResponse> create(@RequestBody CreateRegionRequest regionRequest) {
        return ResponseEntity.ok()
        .body(regionService.createRegion(regionRequest));
    }

    @PutMapping(
        path = "/update/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RegionResponse> update(@PathVariable Integer id, @RequestBody UpdateRegionRequest regionRequest) {
        return ResponseEntity.ok()
        .body(regionService.updateRegion(id, regionRequest));
    }

    @DeleteMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RegionResponse> delete(@PathVariable Integer id) {
        return ResponseEntity.ok()
        .body(regionService.deleteRegion(id));
    }
}