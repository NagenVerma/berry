package com.berrybloomer.berrybloomer.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.berrybloomer.berrybloomer.entity.Login;



@Repository
public interface LoginRepo extends JpaRepository<Login, Long> {


//	Optional<Login> findByUsernameAndPassword( String password,String username);
Optional<Login> findByUsernameAndPassword(String username, String password);




}
