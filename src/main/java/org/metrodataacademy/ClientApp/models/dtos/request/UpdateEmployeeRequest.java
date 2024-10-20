package org.metrodataacademy.ClientApp.models.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployeeRequest {
    
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Integer salary;
    private Float commissionPct;
    private Integer manager;
    private Integer job;
    private Integer department;
}