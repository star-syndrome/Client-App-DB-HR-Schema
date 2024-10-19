package org.metrodataacademy.ClientApp.services.impls;

import java.util.List;

import org.metrodataacademy.ClientApp.models.dtos.request.CreateEmployeeRequest;
import org.metrodataacademy.ClientApp.models.dtos.request.UpdateEmployeeRequest;
import org.metrodataacademy.ClientApp.models.dtos.response.EmployeeResponse;
import org.metrodataacademy.ClientApp.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeServiceImplement implements EmployeeService{

    @Autowired
    private RestTemplate restTemplate;

    private final String url = "http://localhost:8080/employee";

    @Override
    public List<EmployeeResponse> getAll() {
        return restTemplate
        .exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<EmployeeResponse>>() {})
            .getBody();
    }

    @Override
    public EmployeeResponse getById(Integer id) {
        return restTemplate
        .exchange(
            url + "/"+ id,
            HttpMethod.GET,
            null,
            EmployeeResponse.class)
            .getBody();
    }

    @Override
    public EmployeeResponse createEmployee(CreateEmployeeRequest employeeRequest) {
        return restTemplate
        .exchange(
            url,
            HttpMethod.POST,
            new HttpEntity<CreateEmployeeRequest>(employeeRequest),
            EmployeeResponse.class)
            .getBody();
    }

    @Override
    public EmployeeResponse updateEmployee(Integer id, UpdateEmployeeRequest employeeRequest) {
        return restTemplate
        .exchange(
            url + "/" + id,
            HttpMethod.PUT,
            new HttpEntity<UpdateEmployeeRequest>(employeeRequest),
            EmployeeResponse.class)
            .getBody();
    }

    @Override
    public EmployeeResponse deleteEmployee(Integer id) {
        return restTemplate
        .exchange(
            url + "/" + id,
            HttpMethod.DELETE,
            null,
            EmployeeResponse.class)
            .getBody();
    }
}