package org.metrodataacademy.Thymeleaf.services;

import org.metrodataacademy.Thymeleaf.models.dtos.request.LoginRequest;
import org.metrodataacademy.Thymeleaf.models.dtos.request.RegistrationRequest;
import org.metrodataacademy.Thymeleaf.models.dtos.response.ProfileResponse;

public interface AuthService {
    
    Boolean login(LoginRequest loginRequest);

    ProfileResponse registration(RegistrationRequest registrationRequest);
}