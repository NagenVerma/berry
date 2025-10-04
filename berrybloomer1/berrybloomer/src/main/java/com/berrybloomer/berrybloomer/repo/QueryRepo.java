package com.berrybloomer.berrybloomer.repo;

import com.berrybloomer.berrybloomer.entity.ConsultationQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryRepo extends JpaRepository<ConsultationQuery, Long> {

}
