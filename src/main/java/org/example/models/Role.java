package org.example.models;


import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "description")
    private String description;
}
