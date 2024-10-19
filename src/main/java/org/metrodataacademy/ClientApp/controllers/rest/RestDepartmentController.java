package org.metrodataacademy.ClientApp.controllers.rest;

import java.util.List;

import org.metrodataacademy.ClientApp.models.dtos.request.CreateDepartmentRequest;
import org.metrodataacademy.ClientApp.models.dtos.request.UpdateDepartmentRequest;
import org.metrodataacademy.ClientApp.models.dtos.response.DepartmentResponse;
import org.metrodataacademy.ClientApp.services.DepartmentService;
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
@RequestMapping(path = "/api/department")
public class RestDepartmentController {
    
    @Autowired
    private DepartmentService departmentService;
    
    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<DepartmentResponse>> getAll() {
        return ResponseEntity.ok()
        .body(departmentService.getAll());
    }

    @GetMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<DepartmentResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok()
        .body(departmentService.getById(id));
    }

    @PostMapping(
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<DepartmentResponse> create(@RequestBody CreateDepartmentRequest departmentRequest) {
        return ResponseEntity.ok()
        .body(departmentService.createDepartment(departmentRequest));
    }

    @PutMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<DepartmentResponse> update(@PathVariable Integer id, @RequestBody UpdateDepartmentRequest departmentRequest) {
        return ResponseEntity.ok()
        .body(departmentService.updateDepartment(id, departmentRequest));
    }

    @DeleteMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<DepartmentResponse> delete(@PathVariable Integer id) {
        return ResponseEntity.ok()
        .body(departmentService.deleteDepartment(id));
    }
}