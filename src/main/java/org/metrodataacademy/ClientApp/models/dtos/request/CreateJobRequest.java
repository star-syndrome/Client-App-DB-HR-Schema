package org.metrodataacademy.ClientApp.models.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobRequest {

    private String id;
    private String title;
    private Integer minSalary;
    private Integer maxSalary;
}