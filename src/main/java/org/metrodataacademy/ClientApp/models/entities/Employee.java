package org.metrodataacademy.ClientApp.models.entities;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date hireDate;
    private Integer salary;
    private Float commissionPct;
    private Employee manager;
    private Job job;
    private Department department;
    private User user;
}