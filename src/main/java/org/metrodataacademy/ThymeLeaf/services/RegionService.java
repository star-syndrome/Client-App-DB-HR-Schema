package org.metrodataacademy.ThymeLeaf.services;

import java.util.List;

import org.metrodataacademy.ThymeLeaf.models.Region;

public interface RegionService {

    List<Region> getAll();

    Region createRegion(Region region);

    Region getById(Integer id);

    Region updateRegion(Integer id, Region region);

    Region deleteRegion(Integer id);
}