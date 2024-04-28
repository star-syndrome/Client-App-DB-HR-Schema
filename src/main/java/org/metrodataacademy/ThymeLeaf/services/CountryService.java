package org.metrodataacademy.Thymeleaf.services;

import java.util.List;

import org.metrodataacademy.Thymeleaf.models.dtos.request.CreateCountryRequest;
import org.metrodataacademy.Thymeleaf.models.dtos.request.UpdateCountryRequest;
import org.metrodataacademy.Thymeleaf.models.dtos.response.CountryResponse;

public interface CountryService {

    List<CountryResponse> getAll();

    CountryResponse getById(Integer id);
    
    CountryResponse createCountry(CreateCountryRequest countryRequest);

    CountryResponse updateCountry(Integer id, UpdateCountryRequest countryRequest);

    CountryResponse deleteCountry(Integer id);
}