package org.metrodataacademy.ClientApp.models.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateJobRequest {
    
    private String code;
    private String title;
    private Integer minSalary;
    private Integer maxSalary;
}