package org.metrodataacademy.ClientApp.services;

import java.util.List;

import org.metrodataacademy.ClientApp.models.dtos.request.CreateHistoryRequest;
import org.metrodataacademy.ClientApp.models.dtos.response.HistoryResponse;

public interface HistoryService {
    
    List<HistoryResponse> getAll();
    
    HistoryResponse createHistory(CreateHistoryRequest HistoryRequest);

    HistoryResponse deleteHistory(Long id);
}