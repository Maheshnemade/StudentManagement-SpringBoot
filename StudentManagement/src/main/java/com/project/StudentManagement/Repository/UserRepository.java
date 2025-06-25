package com.project.StudentManagement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.StudentManagement.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	
	Optional<User> findByUsername(String username);
}
