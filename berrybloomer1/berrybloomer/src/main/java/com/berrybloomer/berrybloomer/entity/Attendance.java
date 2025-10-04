package com.berrybloomer.berrybloomer.entity;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private LocalDate date;
    private LocalTime timeIn;
    private LocalTime timeOut;
    private boolean present;
    private int workhours;
    
    public String getWorkHours() {
        if (timeIn != null && timeOut != null) {
            Duration duration = Duration.between(timeIn, timeOut);
            long hours = duration.toHours();
            long minutes = duration.toMinutesPart();
            return String.format("%02d:%02d", hours, minutes);
        }
        return "00:00";
    }
}
