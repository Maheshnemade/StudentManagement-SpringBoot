package com.project.StudentManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.project.StudentManagement.Model.Student;
import com.project.StudentManagement.Model.User;
import com.project.StudentManagement.Service.StudentService;
import com.project.StudentManagement.Service.UserService;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:3002", allowCredentials = "true")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public Student getProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);   // Correct method
        return studentService.findByUser(user);
    }

    @PutMapping("/edit")
    public Student updateStudent(@RequestBody Student updated) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);

        Student existing = studentService.findByUser(user);

        updated.setId(existing.getId()); 
        updated.setUser(user);           

        return studentService.updateStudent(updated);
    }
}