package com.project.StudentManagement.Model;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long user_id;

	    @Column(nullable = false, unique = true)
	    private String username;

	    @Column(nullable = false)
	    private String password;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private Role role;
	    
	    @Column(name = "registration_date", nullable = false, updatable = false)
	    private LocalDateTime registrationDate;

	    @PrePersist
	    protected void onCreate() {
	        registrationDate = LocalDateTime.now();
	    }
	    
	    public User() {
	        this.role = Role.STUDENT;
	    }
	    
		public User(Long user_id, String username, String password, Role role) {
			super();
			this.user_id = user_id;
			this.username = username;
			this.password = password;
			this.role = role;
		}

		public Long getUserId() {
			return user_id;
		}

		public void setUserId(Long id) {
			this.user_id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
		}
		
		public LocalDateTime getRegistrationDate() {
	        return registrationDate;
	    }
	    
	    

}


