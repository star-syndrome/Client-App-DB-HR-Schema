package org.metrodataacademy.Thymeleaf.services;

import org.metrodataacademy.Thymeleaf.models.dtos.request.LoginRequest;

public interface AuthService {
    
    Boolean login(LoginRequest loginRequest);
}