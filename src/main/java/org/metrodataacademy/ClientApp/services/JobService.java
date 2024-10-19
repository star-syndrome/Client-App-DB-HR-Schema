package org.metrodataacademy.ClientApp.services;

import java.util.List;

import org.metrodataacademy.ClientApp.models.dtos.request.CreateJobRequest;
import org.metrodataacademy.ClientApp.models.dtos.request.UpdateJobRequest;
import org.metrodataacademy.ClientApp.models.dtos.response.JobResponse;

public interface JobService {
    
    List<JobResponse> getAll();

    JobResponse getById(String id);
    
    JobResponse createJob(CreateJobRequest jobRequest);

    JobResponse updateJob(String id, UpdateJobRequest obRequest);

    JobResponse deleteJob(String id);
}