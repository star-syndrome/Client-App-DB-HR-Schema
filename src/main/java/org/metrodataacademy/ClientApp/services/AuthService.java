package org.metrodataacademy.ClientApp.services;

import org.metrodataacademy.ClientApp.models.dtos.request.LoginRequest;
import org.metrodataacademy.ClientApp.models.dtos.request.RegistrationRequest;
import org.metrodataacademy.ClientApp.models.dtos.response.ProfileResponse;

public interface AuthService {
    
    Boolean login(LoginRequest loginRequest);

    ProfileResponse registration(RegistrationRequest registrationRequest);
}