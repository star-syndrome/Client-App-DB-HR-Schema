package org.metrodataacademy.ClientApp.models.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobResponse {
    
    private String id;
    private String title;
    private Integer minSalary;
    private Integer maxSalary;
}