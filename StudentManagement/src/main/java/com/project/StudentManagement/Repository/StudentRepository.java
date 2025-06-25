package com.project.StudentManagement.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.StudentManagement.Model.Student;
import com.project.StudentManagement.Model.User;

public interface StudentRepository extends JpaRepository<Student, Long>{
	
	Optional<Student> findByEmail(String email);

	List<Student> findByNameContainingIgnoreCase(String name);
	
	Optional<Student> findByUser(User user);

}
