package org.metrodataacademy.ClientApp.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    private String id;
    private String title;
    private Integer minSalary;
    private Integer maxSalary;
}