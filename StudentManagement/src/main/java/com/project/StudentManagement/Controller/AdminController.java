package com.project.StudentManagement.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.StudentManagement.DTO.CourseDTO;
import com.project.StudentManagement.Model.Course;
import com.project.StudentManagement.Model.Role;
import com.project.StudentManagement.Model.Student;
import com.project.StudentManagement.Model.User;
import com.project.StudentManagement.Repository.StudentRepository;
import com.project.StudentManagement.Service.CourseService;
import com.project.StudentManagement.Service.StudentService;
import com.project.StudentManagement.Service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private UserService userService;

 
    @GetMapping("/dashboard")
    public Map<String, Object> getAdminDashboardData() {

        long totalStudents = studentRepository.count();

        Map<String, Object> response = new HashMap<>();
        response.put("totalUsers", totalStudents);

        return response;
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courses = courseService.getAllCourses()
            .stream()
            .map(c -> new CourseDTO(c.getId(), c.getCourseName()))
            .collect(Collectors.toList());

        return ResponseEntity.ok(courses);
    }

    @PostMapping("/courses/add")
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        return ResponseEntity.ok(courseService.addCourse(course));
    }

    @DeleteMapping("/courses/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteCourse(@PathVariable Long id) {
        courseService.DeleteCourseById(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Course deleted successfully!");
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PostMapping("/students/add")
    public ResponseEntity<Student> addStudent(@RequestBody Map<String, Object> payload) {

        
        User user = new User();
        user.setUsername((String) payload.get("username"));
        user.setPassword((String) payload.get("password"));
        user.setRole(Role.STUDENT);
        user = userService.registerUser(user);  

      
        Long courseId = Long.valueOf(payload.get("courseId").toString());
        Course course = courseService.getCourseById(courseId);

       
        Student student = new Student();
        student.setName((String) payload.get("name"));
        student.setEmail((String) payload.get("email"));
        student.setPhoneNumber((String) payload.get("phoneNumber"));
        student.setAddress((String) payload.get("address"));
        student.setAge((Integer) payload.get("age"));
        student.setGender((String) payload.get("gender"));
        student.setUser(user);
        student.setCourse(course);

       
        Student savedStudent = studentService.addStudent(student);

        return ResponseEntity.ok(savedStudent);
    }


    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PutMapping("/students/edit/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @RequestBody Student student) {

        student.setId(id);
        return ResponseEntity.ok(studentService.updateStudentById(id, student));
    }

    @DeleteMapping("/students/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/students/search")
    public ResponseEntity<List<Student>> searchStudents(@RequestParam String keyword) {
        return ResponseEntity.ok(studentService.searchStudents(keyword));
    }
}
