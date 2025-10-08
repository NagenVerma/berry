package com.berrybloomer.berrybloomer.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "leave_request")
@NoArgsConstructor // Ensures JPA can create it with no-arg constructor
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;   // User requesting leave
    private String role;       // Role of the requester
    private String startDate;  // YYYY-MM-DD
    private String endDate;    // YYYY-MM-DD
    private String reason;
    private String status = "Pending"; // Default status
}
