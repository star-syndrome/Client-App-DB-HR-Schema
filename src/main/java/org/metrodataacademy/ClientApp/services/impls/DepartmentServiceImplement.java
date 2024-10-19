package org.metrodataacademy.ClientApp.services.impls;

import java.util.List;

import org.metrodataacademy.ClientApp.models.dtos.request.CreateDepartmentRequest;
import org.metrodataacademy.ClientApp.models.dtos.request.UpdateDepartmentRequest;
import org.metrodataacademy.ClientApp.models.dtos.response.DepartmentResponse;
import org.metrodataacademy.ClientApp.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DepartmentServiceImplement implements DepartmentService {

    @Autowired
    private RestTemplate restTemplate;

    private final String url = "http://localhost:8080/department";

    @Override
    public List<DepartmentResponse> getAll() {
        return restTemplate
        .exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<DepartmentResponse>>() {})
            .getBody();
    }

    @Override
    public DepartmentResponse getById(Integer id) {
        return restTemplate
        .exchange(
            url + "/"+ id,
            HttpMethod.GET,
            null,
            DepartmentResponse.class)
            .getBody();
    }

    @Override
    public DepartmentResponse createDepartment(CreateDepartmentRequest departmentRequest) {
        return restTemplate
        .exchange(
            url,
            HttpMethod.POST,
            new HttpEntity<CreateDepartmentRequest>(departmentRequest),
            DepartmentResponse.class)
            .getBody();
    }

    @Override
    public DepartmentResponse updateDepartment(Integer id, UpdateDepartmentRequest departmentRequest) {
        return restTemplate
        .exchange(
            url + "/" + id,
            HttpMethod.PUT,
            new HttpEntity<UpdateDepartmentRequest>(departmentRequest),
            DepartmentResponse.class)
            .getBody();
    }

    @Override
    public DepartmentResponse deleteDepartment(Integer id) {
        return restTemplate
        .exchange(
            url + "/" + id,
            HttpMethod.DELETE,
            null,
            DepartmentResponse.class)
            .getBody();
    }
}