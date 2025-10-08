package com.berrybloomer.berrybloomer.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.berrybloomer.berrybloomer.entity.Attendance;
import java.util.List;

public interface AttendanceRepo extends JpaRepository<Attendance, Long> {
	   @Query("SELECT a FROM Attendance a WHERE a.username = :username ")
    List<Attendance> findByUserName(String username);
}
