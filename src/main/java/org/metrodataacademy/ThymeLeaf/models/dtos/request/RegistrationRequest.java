package org.metrodataacademy.Thymeleaf.models.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {

    private String name;
    private String email;
    private String phone;
    private String username;
    private String password;
}