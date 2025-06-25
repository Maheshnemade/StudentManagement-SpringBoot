package com.project.StudentManagement.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.StudentManagement.Model.Course;
import com.project.StudentManagement.Repository.CourseRepository;

@Service
public class CourseService {
	
	private final CourseRepository courseRepository;

	public CourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	public Course addCourse(Course course) {
		if (courseRepository.findByCourseName(course.getCourseName()).isPresent()) {
			throw new RuntimeException("Course already exists!");
		}
		return courseRepository.save(course);
	}
	
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}
	
	public void DeleteCourseById(Long courseId) {
		if (!courseRepository.existsById(courseId)) {
			throw new RuntimeException("Course Not Found: " + courseId);
		}
		courseRepository.deleteById(courseId);
	}
	public Course getCourseById(Long id) {
	    return courseRepository.findById(id).orElse(null);
	}
}
