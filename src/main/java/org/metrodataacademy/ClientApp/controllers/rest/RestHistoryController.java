package org.metrodataacademy.ClientApp.controllers.rest;

import java.util.List;

import org.metrodataacademy.ClientApp.models.dtos.request.CreateHistoryRequest;
import org.metrodataacademy.ClientApp.models.dtos.response.HistoryResponse;
import org.metrodataacademy.ClientApp.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/history")
public class RestHistoryController {

    @Autowired
    private HistoryService historyService;
    
    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<HistoryResponse>> getAll() {
        return ResponseEntity.ok()
        .body(historyService.getAll());
    }

    @PostMapping(
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<HistoryResponse> create(@RequestBody CreateHistoryRequest historyRequest) {
        return ResponseEntity.ok()
        .body(historyService.createHistory(historyRequest));
    }

    @DeleteMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<HistoryResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok()
        .body(historyService.deleteHistory(id));
    }
}