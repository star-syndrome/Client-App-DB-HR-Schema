package org.metrodataacademy.ClientApp.models.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponse {
    
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String username;
}