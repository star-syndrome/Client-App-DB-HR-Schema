package org.metrodataacademy.ClientApp.services.impls;

import java.util.List;

import org.metrodataacademy.ClientApp.models.dtos.request.CreateHistoryRequest;
import org.metrodataacademy.ClientApp.models.dtos.response.HistoryResponse;
import org.metrodataacademy.ClientApp.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HistoryServiceImplement implements HistoryService{
    
    @Autowired
    private RestTemplate restTemplate;

    private final String url = "http://localhost:8080/history";

    @Override
    public List<HistoryResponse> getAll() {
        return restTemplate
        .exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<HistoryResponse>>() {})
            .getBody();
    }

    @Override
    public HistoryResponse createHistory(CreateHistoryRequest historyRequest) {
        return restTemplate
        .exchange(
            url,
            HttpMethod.POST,
            new HttpEntity<CreateHistoryRequest>(historyRequest),
            HistoryResponse.class)
            .getBody();
    }

    @Override
    public HistoryResponse deleteHistory(Long id) {
        return restTemplate
        .exchange(
            url + "/" + id,
            HttpMethod.DELETE,
            null,
            HistoryResponse.class)
            .getBody();
    }
}