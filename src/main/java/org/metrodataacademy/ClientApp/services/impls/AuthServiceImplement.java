package org.metrodataacademy.ClientApp.services.impls;

import java.util.List;
import java.util.stream.Collectors;

import org.metrodataacademy.ClientApp.models.dtos.request.LoginRequest;
import org.metrodataacademy.ClientApp.models.dtos.request.RegistrationRequest;
import org.metrodataacademy.ClientApp.models.dtos.response.LoginResponse;
import org.metrodataacademy.ClientApp.models.dtos.response.ProfileResponse;
import org.metrodataacademy.ClientApp.services.AuthService;
import org.metrodataacademy.ClientApp.utils.AuthSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthServiceImplement implements AuthService{

    @Autowired
    private RestTemplate restTemplate;

    private final String url = "http://localhost:8080/auth";

    @Override
    public Boolean login(LoginRequest loginRequest) {
        try {
            ResponseEntity<LoginResponse> res = restTemplate.exchange(
                url + "/login",
                HttpMethod.POST,
                new HttpEntity<>(loginRequest),
                LoginResponse.class
            );

            if (res.getStatusCode() == HttpStatus.OK) {
                setPrinciple(res.getBody(), loginRequest.getPassword());

                Authentication authentication = AuthSessionUtil.getAuthentication();

                log.info("Name: {}", authentication.getName());
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
    
    @Override
    public ProfileResponse registration(RegistrationRequest registrationRequest) {
        try {
            return restTemplate.exchange(
                url + "/registration",
                HttpMethod.POST,
                new HttpEntity<RegistrationRequest>(registrationRequest),
                ProfileResponse.class)
                .getBody();  
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            throw e;
        }
    }

    public void setPrinciple(LoginResponse loginResponse, String password) {
        List<SimpleGrantedAuthority> authorities = loginResponse.getRoles().stream()
        .map(authority -> new SimpleGrantedAuthority(authority))
        .collect(Collectors.toList());

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
            loginResponse.getUsername(),
            password,
            authorities
        );

        SecurityContextHolder.getContext().setAuthentication(token);
    }
}