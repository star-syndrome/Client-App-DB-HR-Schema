package org.metrodataacademy.ClientApp.controllers.rest;

import java.util.List;

import org.metrodataacademy.ClientApp.models.dtos.request.CreateEmployeeRequest;
import org.metrodataacademy.ClientApp.models.dtos.request.UpdateEmployeeRequest;
import org.metrodataacademy.ClientApp.models.dtos.response.EmployeeResponse;
import org.metrodataacademy.ClientApp.services.EmployeeService;
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
@RequestMapping(path = "/api/employee")
public class RestEmployeeController {
    
    @Autowired
    private EmployeeService employeeService;
    
    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<EmployeeResponse>> getAll() {
        return ResponseEntity.ok()
        .body(employeeService.getAll());
    }

    @GetMapping(
        path = "/manager",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<EmployeeResponse>> getAllManager() {
        return ResponseEntity.ok()
        .body(employeeService.getAllManager());
    }

    @GetMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<EmployeeResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok()
        .body(employeeService.getById(id));
    }

    @PostMapping(
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<EmployeeResponse> create(@RequestBody CreateEmployeeRequest employeeRequest) {
        return ResponseEntity.ok()
        .body(employeeService.createEmployee(employeeRequest));
    }

    @PutMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<EmployeeResponse> update(@PathVariable Integer id, @RequestBody UpdateEmployeeRequest employeeRequest) {
        return ResponseEntity.ok()
        .body(employeeService.updateEmployee(id, employeeRequest));
    }

    @DeleteMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<EmployeeResponse> delete(@PathVariable Integer id) {
        return ResponseEntity.ok()
        .body(employeeService.deleteEmployee(id));
    }
}