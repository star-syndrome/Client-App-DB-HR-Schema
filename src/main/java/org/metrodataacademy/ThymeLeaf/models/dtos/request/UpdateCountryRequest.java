package org.metrodataacademy.Thymeleaf.models.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCountryRequest {
    
    private String code;
    private String name;
}