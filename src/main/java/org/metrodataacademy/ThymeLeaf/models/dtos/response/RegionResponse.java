package org.metrodataacademy.Thymeleaf.models.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegionResponse {
    
    private Integer id;
    private String name;
}