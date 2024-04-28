package org.metrodataacademy.Thymeleaf.models.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRegionRequest {
    
    private String name;
}