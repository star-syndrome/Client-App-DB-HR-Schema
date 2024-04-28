package org.metrodataacademy.Thymeleaf.services.impls;

import java.util.List;

import org.metrodataacademy.Thymeleaf.models.dtos.request.CreateCountryRequest;
import org.metrodataacademy.Thymeleaf.models.dtos.request.UpdateCountryRequest;
import org.metrodataacademy.Thymeleaf.models.dtos.response.CountryResponse;
import org.metrodataacademy.Thymeleaf.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CountryServiceImplement implements CountryService{

    @Autowired
    private RestTemplate restTemplate;

    private final String url = "http://localhost:8080/country";

    @Override
    public List<CountryResponse> getAll() {
        return restTemplate
        .exchange(
            url + "/getAll",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<CountryResponse>>() {})
            .getBody();
    }

    @Override
    public CountryResponse getById(Integer id) {
        return restTemplate
        .exchange(
            url + "/"+ id,
            HttpMethod.GET,
            null,
            CountryResponse.class)
            .getBody();
    }
    
    @Override
    public CountryResponse createCountry(CreateCountryRequest countryRequest) {
        return restTemplate
        .exchange(
            url + "/create",
            HttpMethod.POST,
            new HttpEntity<CreateCountryRequest>(countryRequest),
            CountryResponse.class)
            .getBody();
    }

    @Override
    public CountryResponse updateCountry(Integer id, UpdateCountryRequest countryRequest) {
        return restTemplate
        .exchange(
            url + "/update/" + id,
            HttpMethod.PUT,
            new HttpEntity<UpdateCountryRequest>(countryRequest),
            CountryResponse.class)
            .getBody();
    }

    @Override
    public CountryResponse deleteCountry(Integer id) {
        return restTemplate.exchange(
            url + "/delete/" + id,
            HttpMethod.DELETE,
            null,
            CountryResponse.class)
            .getBody();
    }   
}