package org.metrodataacademy.ClientApp.controllers.rest;

import org.metrodataacademy.ClientApp.models.dtos.request.ChangePasswordRequest;
import org.metrodataacademy.ClientApp.models.dtos.request.UpdateUserRequest;
import org.metrodataacademy.ClientApp.models.dtos.response.ProfileResponse;
import org.metrodataacademy.ClientApp.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
public class RestProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping(
        path = "/get",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ProfileResponse> getUser() {
        return ResponseEntity.ok()
        .body(profileService.getUser());
    }

    @PutMapping(
        path = "/update",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ProfileResponse> updateUser(@RequestBody UpdateUserRequest userRequest) {
        return ResponseEntity.ok()
        .body(profileService.updateUser(userRequest));
    }

    @PutMapping(
        path = "/changePassword",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ProfileResponse> changePassword(@RequestBody ChangePasswordRequest passwordRequest) {
        return ResponseEntity.ok()
        .body(profileService.changePassword(passwordRequest));
    }
}