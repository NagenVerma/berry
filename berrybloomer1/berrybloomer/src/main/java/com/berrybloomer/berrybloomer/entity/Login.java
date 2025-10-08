package com.berrybloomer.berrybloomer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Indexed;

@Entity
@Table(name = "login")
@Data
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //@Column(name = "username")// must match exact DB column name
    @Column(name = "username",  nullable = false,unique = true)
    private String username;



    @Column(name = "password",  nullable = false) // must match exact DB column name
    private String password;

    @Column(name = "role")
    private String role;
}
