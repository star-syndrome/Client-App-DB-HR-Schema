package org.metrodataacademy.Thymeleaf.controllers;

import org.metrodataacademy.Thymeleaf.models.dtos.request.ForgotPasswordRequest;
import org.metrodataacademy.Thymeleaf.models.dtos.request.LoginRequest;
import org.metrodataacademy.Thymeleaf.models.dtos.request.RegistrationRequest;
import org.metrodataacademy.Thymeleaf.services.AuthService;
import org.metrodataacademy.Thymeleaf.utils.AuthSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    
    @GetMapping(
        path = "/login",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String loginView(LoginRequest loginRequest) {
        Authentication authentication = AuthSessionUtil.getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken) {
            return "auth/login";
        }
        return "redirect:/home";
    }

    @PostMapping(
        path = "/login",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String login(LoginRequest loginRequest) {
        if (!authService.login(loginRequest)) {
            return "redirect:/login?error=true";
        }
        return "redirect:/home";
    }

    @GetMapping(
        path = "/registration",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String registrationView(RegistrationRequest registrationRequest) {
        return "auth/registration";
    }

    @GetMapping(
        path = "/forgotPassword",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String forgotPasswordView(ForgotPasswordRequest forgotPasswordRequest) {
        return "auth/forgotPassword";
    }
}