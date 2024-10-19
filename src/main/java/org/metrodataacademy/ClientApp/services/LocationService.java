package org.metrodataacademy.ClientApp.services;

import java.util.List;

import org.metrodataacademy.ClientApp.models.dtos.request.CreateLocationRequest;
import org.metrodataacademy.ClientApp.models.dtos.request.UpdateLocationRequest;
import org.metrodataacademy.ClientApp.models.dtos.response.LocationResponse;

public interface LocationService {
    
    List<LocationResponse> getAll();

    LocationResponse getById(Integer id);
    
    LocationResponse createLocation(CreateLocationRequest locationRequest);

    LocationResponse updateLocation(Integer id, UpdateLocationRequest locationRequest);

    LocationResponse deleteLocation(Integer id);
}