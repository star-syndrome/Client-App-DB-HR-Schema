package org.metrodataacademy.ClientApp.models.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateLocationRequest {
    
    private Integer id;
    private String city;
    private String streetAddress;
    private String postalCode;
    private String stateProvince;
    private Integer country;
}