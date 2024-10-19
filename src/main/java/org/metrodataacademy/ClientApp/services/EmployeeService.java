package org.metrodataacademy.ClientApp.services;

import java.util.List;

import org.metrodataacademy.ClientApp.models.dtos.request.CreateEmployeeRequest;
import org.metrodataacademy.ClientApp.models.dtos.request.UpdateEmployeeRequest;
import org.metrodataacademy.ClientApp.models.dtos.response.EmployeeResponse;

public interface EmployeeService {
    
    List<EmployeeResponse> getAll();

    EmployeeResponse getById(Integer id);
    
    EmployeeResponse createEmployee(CreateEmployeeRequest employeeRequest);

    EmployeeResponse updateEmployee(Integer id, UpdateEmployeeRequest employeeRequest);

    EmployeeResponse deleteEmployee(Integer id);
}