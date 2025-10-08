
package com.berrybloomer.berrybloomer.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.berrybloomer.berrybloomer.entity.LeaveRequest;

public interface LeaveRepo extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByUsername(String username);
}
