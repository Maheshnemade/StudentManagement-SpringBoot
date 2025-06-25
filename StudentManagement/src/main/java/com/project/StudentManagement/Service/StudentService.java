package com.project.StudentManagement.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.StudentManagement.Model.Student;
import com.project.StudentManagement.Model.User;
import com.project.StudentManagement.Repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentService {
	
	private final StudentRepository studentRepository; 

	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	@Transactional
	public Student addStudent(Student student) {
		if (studentRepository.findByEmail(student.getEmail()).isPresent()) {
			throw new RuntimeException("Email already exists!");
		}
		return studentRepository.save(student);
	}
	
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	
	public Student getStudentById(Long studentId) {
		return studentRepository.findById(studentId)
				.orElseThrow(() -> new RuntimeException("Student Not Found: " + studentId));
	}
	
	public List<Student> findStudentsByNameContaining(String name) {
		return studentRepository.findByNameContainingIgnoreCase(name);
	}
	@Transactional
	public void deleteStudentById(Long studentId) {
		 if (!studentRepository.existsById(studentId)) {
		        throw new RuntimeException("Student Not Found: " + studentId);
		 }
		studentRepository.deleteById(studentId);
	}
	
	public Student findByUser(User user) {
	    return studentRepository.findByUser(user)
	            .orElseThrow(() -> new RuntimeException("Student not found for user: " + user.getUsername()));
	}
	
	@Transactional
	public Student updateStudent(Student updatedStudent) {
	    User user = updatedStudent.getUser();
	    Student existingStudent = studentRepository.findByUser(user)
	            .orElseThrow(() -> new RuntimeException("Student not found for user: " + user.getUsername()));

	    if (!existingStudent.getEmail().equals(updatedStudent.getEmail()) &&
	        studentRepository.findByEmail(updatedStudent.getEmail()).isPresent()) {
	        throw new RuntimeException("Email already exists!");
	    }

	    existingStudent.setName(updatedStudent.getName());
	    existingStudent.setEmail(updatedStudent.getEmail());
	    existingStudent.setPhoneNumber(updatedStudent.getPhoneNumber());
	    existingStudent.setAddress(updatedStudent.getAddress());
	    existingStudent.setAge(updatedStudent.getAge());
	    existingStudent.setCourse(updatedStudent.getCourse());

	    return studentRepository.save(existingStudent);
	}

	@Transactional
	public Student updateStudentById(Long studentId, Student updatedStudent) {
	    Student existingStudent = studentRepository.findById(studentId)
	            .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));

	    if (!existingStudent.getEmail().equals(updatedStudent.getEmail()) &&
	        studentRepository.findByEmail(updatedStudent.getEmail()).isPresent()) {
	        throw new RuntimeException("Email already exists!");
	    }

	    existingStudent.setName(updatedStudent.getName());
	    existingStudent.setEmail(updatedStudent.getEmail());
	    existingStudent.setPhoneNumber(updatedStudent.getPhoneNumber());
	    existingStudent.setAddress(updatedStudent.getAddress());
	    existingStudent.setAge(updatedStudent.getAge());
	    existingStudent.setCourse(updatedStudent.getCourse());

	    return studentRepository.save(existingStudent);
	}


	
	

}
