package org.metrodataacademy.ClientApp.models.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    private Integer id;
    private String username;
    private String password;
    private List<Role> roles;
}