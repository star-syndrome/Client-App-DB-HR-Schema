package org.metrodataacademy.Thymeleaf.services.impls;

import java.util.List;

import org.metrodataacademy.Thymeleaf.models.Region;
import org.metrodataacademy.Thymeleaf.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RegionServiceImplement implements RegionService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Region> getAll() {
        return restTemplate
        .exchange(
            "http://localhost:8080/region/getAll",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Region>>() {})
            .getBody();
    }

    @Override
    public Region createRegion(Region region) {
        return restTemplate
        .exchange(
            "http://localhost:8080/region/create",
            HttpMethod.POST,
            new HttpEntity<Region>(region),
            new ParameterizedTypeReference<Region>() {})
            .getBody();
    }

    @Override
    public Region getById(Integer id) {
        return restTemplate
        .exchange(
            "http://localhost:8080/region/" + id,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Region>() {})
            .getBody();
    }

    @Override
    public Region updateRegion(Integer id, Region region) {
        return restTemplate
        .exchange(
            "http://localhost:8080/region/update/" + id,
            HttpMethod.PUT,
            new HttpEntity<Region>(region),
            new ParameterizedTypeReference<Region>() {})
            .getBody();
    }

    @Override
    public Region deleteRegion(Integer id) {
        return restTemplate
        .exchange(
            "http://localhost:8080/region/delete/" + id,
            HttpMethod.DELETE,
            null,
            new ParameterizedTypeReference<Region>() {})
            .getBody();
    }   
}