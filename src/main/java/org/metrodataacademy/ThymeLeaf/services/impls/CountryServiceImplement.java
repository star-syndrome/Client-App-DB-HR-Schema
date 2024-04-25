package org.metrodataacademy.Thymeleaf.services.impls;

import java.util.List;

import org.metrodataacademy.Thymeleaf.models.Country;
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

    @Override
    public List<Country> getAll() {
        return restTemplate
        .exchange(
            "http://localhost:8080/country/getAll",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Country>>() {})
            .getBody();
    }

    @Override
    public Country getById(Integer id) {
        return restTemplate
        .exchange(
            "http://localhost:8080/country/"+ id,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Country>() {})
            .getBody();
    }
    
    @Override
    public Country createCountry(Country country) {
        return restTemplate
        .exchange(
            "http://localhost:8080/country/create",
            HttpMethod.POST,
            new HttpEntity<Country>(country),
            new ParameterizedTypeReference<Country>() {})
            .getBody();
    }

    @Override
    public Country updateCountry(Integer id, Country country) {
        return restTemplate
        .exchange(
            "http://localhost:8080/country/update/" + id,
            HttpMethod.PUT,
            new HttpEntity<Country>(country),
            new ParameterizedTypeReference<Country>() {})
            .getBody();
    }

    @Override
    public Country deleteCountry(Integer id) {
        return restTemplate.exchange(
            "http://localhost:8080/country/delete/" + id,
            HttpMethod.DELETE,
            null,
            new ParameterizedTypeReference<Country>() {})
            .getBody();
    }   
}