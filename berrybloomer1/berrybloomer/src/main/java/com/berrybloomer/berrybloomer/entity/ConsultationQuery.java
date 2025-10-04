package com.berrybloomer.berrybloomer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "consultation_queries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultationQuery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    private BigDecimal budget;

    /**
     * Comma-separated string, e.g., "ANDROID_APP,IOS_APP"
     */
    @Column(name = "platforms_required")
    private String platformsRequired;

    private String location;

    private String suitableTimeToCall;

    @Column(columnDefinition = "TEXT")
    private String message;

}
