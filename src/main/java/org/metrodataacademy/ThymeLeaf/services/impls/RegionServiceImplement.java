package org.metrodataacademy.Thymeleaf.services.impls;

import java.util.List;

import org.metrodataacademy.Thymeleaf.models.dtos.request.CreateRegionRequest;
import org.metrodataacademy.Thymeleaf.models.dtos.request.UpdateRegionRequest;
import org.metrodataacademy.Thymeleaf.models.dtos.response.RegionResponse;
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

    private final String url = "http://localhost:8080/region";

    @Override
    public List<RegionResponse> getAll() {
        return restTemplate
        .exchange(
            url + "/getAll",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<RegionResponse>>() {})
            .getBody();
    }

    @Override
    public RegionResponse createRegion(CreateRegionRequest regionRequest) {
        return restTemplate
        .exchange(
            url + "/create",
            HttpMethod.POST,
            new HttpEntity<CreateRegionRequest>(regionRequest),
            RegionResponse.class)
            .getBody();
    }

    @Override
    public RegionResponse getById(Integer id) {
        return restTemplate
        .exchange(
            url + "/" + id,
            HttpMethod.GET,
            null,
            RegionResponse.class)
            .getBody();
    }

    @Override
    public RegionResponse updateRegion(Integer id, UpdateRegionRequest regionRequest) {
        return restTemplate
        .exchange(
            url + "/update/" + id,
            HttpMethod.PUT,
            new HttpEntity<UpdateRegionRequest>(regionRequest),
            RegionResponse.class)
            .getBody();
    }

    @Override
    public RegionResponse deleteRegion(Integer id) {
        return restTemplate
        .exchange(
            url + "/delete/" + id,
            HttpMethod.DELETE,
            null,
            RegionResponse.class)
            .getBody();
    }   
}