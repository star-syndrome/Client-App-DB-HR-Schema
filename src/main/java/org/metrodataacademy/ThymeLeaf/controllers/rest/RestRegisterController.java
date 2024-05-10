package org.metrodataacademy.Thymeleaf.controllers.rest;

import org.metrodataacademy.Thymeleaf.models.dtos.request.RegistrationRequest;
import org.metrodataacademy.Thymeleaf.models.dtos.response.ProfileResponse;
import org.metrodataacademy.Thymeleaf.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class RestRegisterController {
    
    @Autowired
    private AuthService authService;

    @PostMapping(
        path = "/registration",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ProfileResponse> registration(@RequestBody RegistrationRequest registrationRequest) {
        return ResponseEntity.ok()
        .body(authService.registration(registrationRequest));
    }
}