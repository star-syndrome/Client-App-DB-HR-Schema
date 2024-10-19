package org.metrodataacademy.ClientApp.models.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class History {

    private Long id;
    private Date startDate;
    private Date endDate;
    private Department department;
    private Employee employee;
    private Job job;
}