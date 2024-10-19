package org.metrodataacademy.ClientApp.models.dtos.response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryResponse {
    
    private Long id;
    private String startDate;
    private String endDate;
    private String department;
    private String employee;
    private String job;
}