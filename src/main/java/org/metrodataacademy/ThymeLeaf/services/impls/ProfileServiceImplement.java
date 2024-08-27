package org.metrodataacademy.Thymeleaf.services.impls;

import org.metrodataacademy.Thymeleaf.models.dtos.request.ChangePasswordRequest;
import org.metrodataacademy.Thymeleaf.models.dtos.request.UpdateUserRequest;
import org.metrodataacademy.Thymeleaf.models.dtos.response.ProfileResponse;
import org.metrodataacademy.Thymeleaf.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ProfileServiceImplement implements ProfileService {

    @Autowired
    private RestTemplate restTemplate;

    private final String url = "http://localhost:8080/profile";

    @Override
    public ProfileResponse getUser() {
        return restTemplate
        .exchange(
            url,
            HttpMethod.GET,
            null,
            ProfileResponse.class)
            .getBody();
    }

    @Override
    public ProfileResponse updateUser(UpdateUserRequest userRequest) {
        return restTemplate
        .exchange(
            url,
            HttpMethod.PUT,
            new HttpEntity<UpdateUserRequest>(userRequest),
            ProfileResponse.class)
            .getBody();
    }

    @Override
    public ProfileResponse changePassword(ChangePasswordRequest passwordRequest) {
        return restTemplate
        .exchange(
            url + "/change-password",
            HttpMethod.PUT,
            new HttpEntity<ChangePasswordRequest>(passwordRequest),
            ProfileResponse.class)
            .getBody();
    }
}