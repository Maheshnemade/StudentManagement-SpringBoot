package com.project.StudentManagement.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.StudentManagement.Model.Role;
import com.project.StudentManagement.Model.User;
import com.project.StudentManagement.Repository.UserRepository;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Service
public class UserService {
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	public UserService( PasswordEncoder passwordEncoder, UserRepository UserRepository) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = UserRepository;
	}
	
	@Transactional
	public User registerUser(User user) {
		if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists!");
        }
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(Role.STUDENT);
		return userRepository.save(user);
	}
	
	public User getLoggedInUser() {
	    String username = SecurityContextHolder.getContext().getAuthentication().getName();
	    return userRepository.findByUsername(username)
	           .orElseThrow(() -> new RuntimeException("User not found!"));
	}
	
	// Not implemented created for future use if multiple Admins are needed
	@Transactional
	public User registerAdmin(User newAdmin) {
		
		User loggedInUser = getLoggedInUser();
		
		if (loggedInUser.getRole() != Role.ADMIN) {
			throw new RuntimeException("Only admins can create new admins!");
		}
		if (userRepository.findByUsername(newAdmin.getUsername()).isPresent()) {
			throw new RuntimeException("Username already exists!");
		}
		newAdmin.setPassword(passwordEncoder.encode(newAdmin.getPassword()));
		newAdmin.setRole(Role.ADMIN);
		return userRepository.save(newAdmin);
	}
	
	public User loginUser(String username, String password) {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found!"));

		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new RuntimeException("Invalid password!");
		}
		return user;
	}
	
	 	@Value("${admin.username}") 
	    private String adminUsername;

	    @Value("${admin.password}")
	    private String adminPassword;

	    @Value("${admin.role}")
	    private String adminRole;


	    @PostConstruct
	    public void createAdminIfNotExists() {
	        if (userRepository.findByUsername(adminUsername).isEmpty()) { 
	            User admin = new User();
	            admin.setUsername(adminUsername);
	            admin.setPassword(passwordEncoder.encode(adminPassword)); 
	            admin.setRole(Role.valueOf(adminRole));
	            userRepository.save(admin);
	            System.out.println("Admin user created: " + adminUsername);
	        }
	    }
	    
	    public User findByUsername(String username) {
	        return userRepository.findByUsername(username)
	                .orElseThrow(() -> new RuntimeException("User not found"));
	    }
	    
	    @Transactional
	    public User getUserById(Long id) {
	        return userRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
	    }
	
	
}
