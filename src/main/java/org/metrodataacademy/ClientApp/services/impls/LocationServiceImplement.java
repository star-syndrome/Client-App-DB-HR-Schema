package org.metrodataacademy.ClientApp.services.impls;

import java.util.List;

import org.metrodataacademy.ClientApp.models.dtos.request.CreateLocationRequest;
import org.metrodataacademy.ClientApp.models.dtos.request.UpdateLocationRequest;
import org.metrodataacademy.ClientApp.models.dtos.response.LocationResponse;
import org.metrodataacademy.ClientApp.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LocationServiceImplement implements LocationService {

    @Autowired
    private RestTemplate restTemplate;

    private final String url = "http://localhost:8080/location";

    @Override
    public List<LocationResponse> getAll() {
        return restTemplate
        .exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<LocationResponse>>() {})
            .getBody();
    }

    @Override
    public LocationResponse getById(Integer id) {
        return restTemplate
        .exchange(
            url + "/"+ id,
            HttpMethod.GET,
            null,
            LocationResponse.class)
            .getBody();
    }

    @Override
    public LocationResponse createLocation(CreateLocationRequest locationRequest) {
        return restTemplate
        .exchange(
            url,
            HttpMethod.POST,
            new HttpEntity<CreateLocationRequest>(locationRequest),
            LocationResponse.class)
            .getBody();
    }

    @Override
    public LocationResponse updateLocation(Integer id, UpdateLocationRequest locationRequest) {
        return restTemplate
        .exchange(
            url + "/" + id,
            HttpMethod.PUT,
            new HttpEntity<UpdateLocationRequest>(locationRequest),
            LocationResponse.class)
            .getBody();
    }

    @Override
    public LocationResponse deleteLocation(Integer id) {
        return restTemplate
        .exchange(
            url + "/" + id,
            HttpMethod.DELETE,
            null,
            LocationResponse.class)
            .getBody();
    }   
}