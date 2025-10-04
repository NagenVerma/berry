package com.berrybloomer.berrybloomer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "login")
@Data
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username") // must match exact DB column name
    private String username;

    @Column(name = "password") // must match exact DB column name
    private String password;

    @Column(name = "role")
    private String role;
}
