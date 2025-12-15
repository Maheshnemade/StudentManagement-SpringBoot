package com.project.StudentManagement.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.StudentManagement.Model.Course;
import com.project.StudentManagement.Model.Student;
import com.project.StudentManagement.Model.User;
import com.project.StudentManagement.Repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentService {
	
	 private final StudentRepository studentRepository;
	    private final CourseService courseService;
	    private final UserService userService;

	    public StudentService(StudentRepository studentRepository,
	                          CourseService courseService,
	                          UserService userService) {
	        this.studentRepository = studentRepository;
	        this.courseService = courseService;
	        this.userService = userService;
	    }
	
	  @Transactional
	    public Student addStudent(Map<String, Object> payload) {
	        Student student = new Student();
	        student.setName((String) payload.get("name"));
	        student.setEmail((String) payload.get("email"));
	        student.setPhoneNumber((String) payload.get("phoneNumber"));
	        student.setAddress((String) payload.get("address"));
	        student.setAge((Integer) payload.get("age"));
	        student.setGender((String) payload.get("gender"));

	        Long courseId = Long.valueOf(payload.get("courseId").toString());
	        Course course = courseService.getCourseById(courseId); 
	        student.setCourse(course);

	        Long userId = Long.valueOf(payload.get("userId").toString());
	        User user = userService.getUserById(userId); 
	        student.setUser(user);

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
	public List<Student> searchStudents(String keyword) {
	    String searchKey = keyword.toLowerCase();

	    return studentRepository.findAll().stream()
	            .filter(student ->
	                    (student.getName() != null &&
	                     student.getName().toLowerCase().contains(searchKey))

	                 || (student.getEmail() != null &&
	                     student.getEmail().toLowerCase().contains(searchKey))

	                 || (student.getAddress() != null &&
	                     student.getAddress().toLowerCase().contains(searchKey))

	                 || (student.getPhoneNumber() != null &&
	                     student.getPhoneNumber().contains(searchKey))
	            )
	            .collect(Collectors.toList());
	}
	 
	 @Transactional
	 public Student addStudent(Student student) {
	     return studentRepository.save(student);
	 }

	
	

}
