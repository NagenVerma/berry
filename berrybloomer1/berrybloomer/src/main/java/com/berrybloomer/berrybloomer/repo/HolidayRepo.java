package com.berrybloomer.berrybloomer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.berrybloomer.berrybloomer.entity.Holiday;

@Repository
public interface HolidayRepo extends JpaRepository<Holiday, Long> { 
	
}

