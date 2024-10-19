package org.metrodataacademy.ClientApp.services.impls;

import java.util.List;

import org.metrodataacademy.ClientApp.models.dtos.request.CreateJobRequest;
import org.metrodataacademy.ClientApp.models.dtos.request.UpdateJobRequest;
import org.metrodataacademy.ClientApp.models.dtos.response.JobResponse;
import org.metrodataacademy.ClientApp.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JobServiceImplement implements JobService{
    
    @Autowired
    private RestTemplate restTemplate;

    private final String url = "http://localhost:8080/job";

    @Override
    public List<JobResponse> getAll() {
        return restTemplate
        .exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<JobResponse>>() {})
            .getBody();
    }

    @Override
    public JobResponse getById(String id) {
        return restTemplate
        .exchange(
            url + "/"+ id,
            HttpMethod.GET,
            null,
            JobResponse.class)
            .getBody();
    }

    @Override
    public JobResponse createJob(CreateJobRequest jobRequest) {
        return restTemplate
        .exchange(
            url,
            HttpMethod.POST,
            new HttpEntity<CreateJobRequest>(jobRequest),
            JobResponse.class)
            .getBody();
    }

    @Override
    public JobResponse updateJob(String id, UpdateJobRequest jobRequest) {
        return restTemplate
        .exchange(
            url + "/" + id,
            HttpMethod.PUT,
            new HttpEntity<UpdateJobRequest>(jobRequest),
            JobResponse.class)
            .getBody();
    }

    @Override
    public JobResponse deleteJob(String id) {
        return restTemplate
        .exchange(
            url + "/" + id,
            HttpMethod.DELETE,
            null,
            JobResponse.class)
            .getBody();
    }
}