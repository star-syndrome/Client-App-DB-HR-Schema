package org.metrodataacademy.ClientApp.models.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponse {
    
    private Integer id;
    private String name;
    private String location;
    private String manager;
}