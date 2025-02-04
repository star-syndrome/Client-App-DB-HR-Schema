package org.metrodataacademy.ClientApp.models.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    
    private Integer id;
    private String name;
    private List<Privilege> privileges;
}