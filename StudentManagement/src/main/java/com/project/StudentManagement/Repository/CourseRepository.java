package com.project.StudentManagement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.StudentManagement.Model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

	Optional<Course> findByCourseName(String courseName);
	

}
