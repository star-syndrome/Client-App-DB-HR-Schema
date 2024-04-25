package org.metrodataacademy.Thymeleaf.services;

import java.util.List;

import org.metrodataacademy.Thymeleaf.models.Region;

public interface RegionService {

    List<Region> getAll();

    Region createRegion(Region region);

    Region getById(Integer id);

    Region updateRegion(Integer id, Region region);

    Region deleteRegion(Integer id);
}