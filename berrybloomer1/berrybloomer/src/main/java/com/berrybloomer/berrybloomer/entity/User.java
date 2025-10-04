package com.berrybloomer.berrybloomer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "users")  // <-- rename to avoid keyword conflict
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String username;
    private String desingnation; // (You may want to correct this typo)
    private String role;
	//public String LoginStatus;
	public boolean LoginStatus;
}
