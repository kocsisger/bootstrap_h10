package hu.unideb.inf.bootstrap.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Persons")
public class Person {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean enabled;
}
