package com.project.StudentManagement.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.StudentManagement.Model.Student;
import com.project.StudentManagement.Model.User;
import com.project.StudentManagement.Service.StudentService;
import com.project.StudentManagement.Service.UserService;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	private final StudentService studentService;
	private final UserService userService;
	public StudentController(StudentService studentService, UserService userService) {
        this.studentService = studentService;
        this.userService = userService;
    }
	
	
	@GetMapping("/dashboard")
	public String showStudentDashboard(Model model) {
	    User currentUser = userService.getLoggedInUser();
	    Student student = studentService.findByUser(currentUser);
	    model.addAttribute("student", student);
	    return "student-dashboard"; 
	}
	
	@GetMapping("/edit")
	public String editStudentForm(Model model) {
		User currentUser = userService.getLoggedInUser();
		Student student = studentService.findByUser(currentUser);
		model.addAttribute("student", student);
		model.addAttribute("isAdmin", false);
		return "edit-student";
	}
	
	@PostMapping("/edit")
	public String updateStudent(@ModelAttribute("student") Student updatedStudent) {
	    User currentUser = userService.getLoggedInUser();
	    Student existingStudent = studentService.findByUser(currentUser);

	    updatedStudent.setId(existingStudent.getId()); 
	    updatedStudent.setUser(currentUser);          
	    updatedStudent.setCourse(existingStudent.getCourse()); 

	    studentService.updateStudent(updatedStudent);
	    return "redirect:/student/dashboard";
	}
	
//	Email Confirmation
//	Send a confirmation mail after successful registration using Spring Mail.
//
//	Export to CSV/Excel
//	Admin can download student list with course info for reporting.
	
}
