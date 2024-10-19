package org.metrodataacademy.ClientApp.controllers.rest;

import java.util.List;

import org.metrodataacademy.ClientApp.models.dtos.request.CreateJobRequest;
import org.metrodataacademy.ClientApp.models.dtos.request.UpdateJobRequest;
import org.metrodataacademy.ClientApp.models.dtos.response.JobResponse;
import org.metrodataacademy.ClientApp.services.JobService;
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
@RequestMapping(path = "/api/job")
public class RestJobController {

    @Autowired
    private JobService jobService;
    
    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<JobResponse>> getAll() {
        return ResponseEntity.ok()
        .body(jobService.getAll());
    }

    @GetMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<JobResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok()
        .body(jobService.getById(id));
    }

    @PostMapping(
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<JobResponse> create(@RequestBody CreateJobRequest jobRequest) {
        return ResponseEntity.ok()
        .body(jobService.createJob(jobRequest));
    }

    @PutMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<JobResponse> update(@PathVariable String id, @RequestBody UpdateJobRequest jobRequest) {
        return ResponseEntity.ok()
        .body(jobService.updateJob(id, jobRequest));
    }

    @DeleteMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<JobResponse> delete(@PathVariable String id) {
        return ResponseEntity.ok()
        .body(jobService.deleteJob(id));
    }
}