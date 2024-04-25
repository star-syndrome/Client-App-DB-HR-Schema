package org.metrodataacademy.ThymeLeaf.services;

import java.util.List;

import org.metrodataacademy.ThymeLeaf.models.Country;

public interface CountryService {

    List<Country> getAll();

    Country getById(Integer id);
    
    Country createCountry(Country country);

    Country updateCountry(Integer id, Country country);

    Country deleteCountry(Integer id);
}