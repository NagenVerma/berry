package com.berrybloomer.berrybloomer.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.berrybloomer.berrybloomer.entity.User;

@Repository
public interface userRepo extends JpaRepository<User, Long> {
    
//    @Query("SELECT u FROM User u WHERE u.username = :username and password =:password")
//    User findByUserName(@Param("username") String username,@Param("password") String password);
}




