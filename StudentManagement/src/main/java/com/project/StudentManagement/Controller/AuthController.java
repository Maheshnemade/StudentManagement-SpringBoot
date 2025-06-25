package com.project.StudentManagement.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.project.StudentManagement.Model.*;
import com.project.StudentManagement.Repository.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/")
	public String showIndexPage() {
		return "index";
	}

	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login?logout=true";
	}

	// Show registration form with course list
	@GetMapping("/register")
	public String registerUser(Model model) {
		model.addAttribute("courses", courseRepository.findAll());
		return "register";
	}

	//Handle registration logic (User + Student + Course)
	@PostMapping("/register")
	public String registerUser(@RequestParam String username, @RequestParam String password, @RequestParam String name,
			@RequestParam String email, @RequestParam String phoneNumber, @RequestParam String address,
			@RequestParam Integer age, @RequestParam Long courseId) {

		Optional<User> existingUser = userRepository.findByUsername(username);
		if (existingUser.isPresent()) {
			return "redirect:/register?error=exists";
		}

		// Create and save user
		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		user.setRole(Role.STUDENT);
		userRepository.save(user);

		// Find course
		Course course = courseRepository.findById(courseId).orElse(null);

		// Create and save student
		Student student = new Student();
		student.setName(name);
		student.setEmail(email);
		student.setPhoneNumber(phoneNumber);
		student.setAddress(address);
		student.setAge(age);
		student.setCourse(course);
		student.setUser(user);
		studentRepository.save(student);

		return "redirect:/login?register=success";
	}

	//Password reset functionality
	@GetMapping("/password-reset")
	public String showPasswordResetPage() {
		return "password-reset";
	}

	@PostMapping("/password-reset")
	public String resetPassword(@RequestParam String username, @RequestParam String newPassword,
			@RequestParam String confirmPassword, Model model) {

		if (!newPassword.equals(confirmPassword)) {
			model.addAttribute("error", "New password and Confirm password do not match.");
			return "password-reset";
		}

		User user = userRepository.findByUsername(username).orElse(null);
		if (user != null) {
			user.setPassword(passwordEncoder.encode(newPassword));
			userRepository.save(user);
			return "redirect:/login";
		} else {
			model.addAttribute("error", "User not found.");
			return "password-reset";
		}
	}
}
