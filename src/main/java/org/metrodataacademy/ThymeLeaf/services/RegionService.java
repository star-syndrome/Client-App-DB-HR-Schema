package org.metrodataacademy.Thymeleaf.services;

import java.util.List;

import org.metrodataacademy.Thymeleaf.models.dtos.request.CreateRegionRequest;
import org.metrodataacademy.Thymeleaf.models.dtos.request.UpdateRegionRequest;
import org.metrodataacademy.Thymeleaf.models.dtos.response.RegionResponse;

public interface RegionService {

    List<RegionResponse> getAll();

    RegionResponse createRegion(CreateRegionRequest regionRequest);

    RegionResponse getById(Integer id);

    RegionResponse updateRegion(Integer id, UpdateRegionRequest regionRequest);

    RegionResponse deleteRegion(Integer id);
}