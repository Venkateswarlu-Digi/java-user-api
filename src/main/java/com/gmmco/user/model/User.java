package com.gmmco.user.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    private Integer id;

    @Column(name = "emp_id")
    private String empId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;


}
