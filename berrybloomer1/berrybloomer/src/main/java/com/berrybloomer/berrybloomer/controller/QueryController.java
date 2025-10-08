package com.berrybloomer.berrybloomer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.berrybloomer.berrybloomer.entity.Attendance;
import com.berrybloomer.berrybloomer.entity.ConsultationQuery;
import com.berrybloomer.berrybloomer.entity.Holiday;

import com.berrybloomer.berrybloomer.entity.LeaveRequest;
import com.berrybloomer.berrybloomer.entity.Login;
import com.berrybloomer.berrybloomer.entity.User;
import com.berrybloomer.berrybloomer.repo.HolidayRepo;
import com.berrybloomer.berrybloomer.repo.LeaveRepo;
import com.berrybloomer.berrybloomer.repo.LoginRepo;
import com.berrybloomer.berrybloomer.repo.QueryRepo;
import com.berrybloomer.berrybloomer.repo.userRepo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/webapp")
public class QueryController {

	@Autowired
	private QueryRepo queryRepo;

	@Autowired
	private userRepo userRepo;

	@Autowired
	private LoginRepo loginRepo;
	@Autowired
	private HolidayRepo holidayrepo;
	@Autowired
	private LeaveRepo leaveRepo;

	@Autowired
	private AttendanceRepo attendanceRepo;

	@PostMapping("/queries")
	public ResponseEntity<String> saveQuery(@RequestBody ConsultationQuery queryEntity) {
		queryRepo.save(queryEntity);
		return ResponseEntity.ok("Query Submitted");
	}

	@PostMapping("/create/users")
	public ResponseEntity<?> createUser(@RequestBody Login user) {
		loginRepo.save(user);
		return ResponseEntity.ok("User created successfully!");
	}

	@PostMapping("/login")
	public ResponseEntity<Object> loginUser(@RequestBody Login authRequest) {
		String username = authRequest.getUsername();
		String password = authRequest.getPassword();

		//change




//		@Id
//		@GeneratedValue(strategy = GenerationType.IDENTITY)
//		private Long id;
//		private String userId;
//		private String username;
//		private String desingnation; // (You may want to correct this typo)
//		private String role;
//		//public String LoginStatus;
//		public boolean loginStatus;
//
//		User userAdd = new User();
//		userAdd.setId(authRequest.getId());
//		userAdd.setUsername(authRequest.getUsername());
//		userAdd.setRole(authRequest.getRole());
//		userAdd.setLoginStatus(true);
//		userRepo.save(userAdd);







		// Fetch user from DB
	//	Optional<Login> userDetail = loginRepo.findByUsernameAndPassword("abc", "abc");

		Optional<Login>userDetail = loginRepo.findByUsernameAndPassword(username, password);


		// Check if user exists
		if (userDetail.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
		}

		// Extract actual user object
		Login user = userDetail.get();
		String role = user.getRole();



		User userAdd = new User();
		userAdd.setId(user.getId());
		userAdd.setUsername(authRequest.getUsername());
		userAdd.setRole(authRequest.getRole());
		userAdd.setLoginStatus(true);
		userRepo.save(userAdd);





		// Role-based data
		if (role.equalsIgnoreCase("Admin") || role.equalsIgnoreCase("CE")) {
			List<ConsultationQuery> queries = getAllConsultationQueries();
			return ResponseEntity.ok(queries);
		} else if (role.equalsIgnoreCase("HR")) {
			Map<String, Object> data = new HashMap<>();
			// add HR-specific data here
			return ResponseEntity.ok(data);
		} else if (role.equalsIgnoreCase("IT")) {
			Map<String, Object> data = new HashMap<>();
			// add IT-specific data here
			return ResponseEntity.ok(data);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Role not allowed");
		}
	}

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	// GET all consultation queries
	@GetMapping("/consultation-queries")
	public List<ConsultationQuery> getAllConsultationQueries() {
		return queryRepo.findAll();
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout(@RequestParam Long id) {
		Optional<User> optionalUser = userRepo.findById(id);

		if (optionalUser.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
		}

		User user = optionalUser.get();
		user.setLoginStatus(false);
		userRepo.save(user);

		return ResponseEntity.ok("Logged out successfully.");
	}

	@PostMapping
	public ResponseEntity<String> saveAttendance(@RequestBody Attendance attendance) {
		attendanceRepo.save(attendance);
		return ResponseEntity.ok("Attendance submitted successfully");
	}

	// ✅ 2. Get Attendance by User ID
	@GetMapping("/{username}")
	public ResponseEntity<List<Attendance>> getAttendanceByUserName(@PathVariable String username) {
		List<Attendance> list = attendanceRepo.findByUserName(username);
		return ResponseEntity.ok(list);
	}

	@GetMapping("/getAllAttendance")
	public ResponseEntity<List<Attendance>> getAllAttendance() {
		List<Attendance> list = attendanceRepo.findAll();
		return ResponseEntity.ok(list);
	}

	// Add a holiday
	@PostMapping("/holiday")
	public ResponseEntity<String> addHoliday(@RequestBody Holiday holiday) {
		holidayrepo.save(holiday);
		return ResponseEntity.ok("Holiday added successfully");
	}

	// Get all holidays by location
	@GetMapping("/holidays")
	public ResponseEntity<List<Holiday>> getHolidaysByLocation(@RequestParam String location) {
		List<Holiday> holidays = holidayrepo.findAll();
		if (holidays.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(holidays);
		}
		return ResponseEntity.ok(holidays);
	}

	// Save leave request
	@PostMapping("/leave")
	public ResponseEntity<String> saveLeave(@RequestBody LeaveRequest leaveRequest) {
		leaveRepo.save(leaveRequest);
		return ResponseEntity.ok("Leave request submitted successfully");
	}

	// Get all leaves for a username
	@GetMapping("/leave/{username}")
	public List<LeaveRequest> getLeavesByUsername(@PathVariable String username) {
		return leaveRepo.findByUsername(username);
	}

}
