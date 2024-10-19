package org.metrodataacademy.ClientApp.services;

import java.util.List;

import org.metrodataacademy.ClientApp.models.dtos.request.CreateDepartmentRequest;
import org.metrodataacademy.ClientApp.models.dtos.request.UpdateDepartmentRequest;
import org.metrodataacademy.ClientApp.models.dtos.response.DepartmentResponse;

public interface DepartmentService {
    
    List<DepartmentResponse> getAll();

    DepartmentResponse getById(Integer id);
    
    DepartmentResponse createDepartment(CreateDepartmentRequest departmentRequest);

    DepartmentResponse updateDepartment(Integer id, UpdateDepartmentRequest departmentRequest);

    DepartmentResponse deleteDepartment(Integer id);
}