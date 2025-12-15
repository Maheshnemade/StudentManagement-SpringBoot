package com.project.StudentManagement.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import com.project.StudentManagement.Model.Course;
import com.project.StudentManagement.Model.Student;
import com.project.StudentManagement.Model.User;
import com.project.StudentManagement.Model.Role;
import com.project.StudentManagement.Repository.CourseRepository;
import com.project.StudentManagement.Repository.StudentRepository;
import com.project.StudentManagement.Repository.UserRepository;
import com.project.StudentManagement.Service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:3002"}, allowCredentials = "true")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        String username = body.get("username");
        String password = body.get("password");

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

           
            SecurityContextHolder.getContext().setAuthentication(authentication);

           
            request.getSession(true); 
            new HttpSessionSecurityContextRepository()
                    .saveContext(SecurityContextHolder.getContext(), request, response);

            
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found after authentication"));

            Student student = null;
            if (user.getRole() == Role.STUDENT) {
                student = studentRepository.findByUser(user).orElse(null);
            }

            Map<String, Object> resp = new HashMap<>();
            resp.put("message", "Login successful");
            resp.put("user", user);
            if (student != null) resp.put("student", student);

            return ResponseEntity.ok(resp);

        } catch (Exception ex) {
            logger.error("Login error: {}", ex.getMessage(), ex);
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        }
    }

    
    @GetMapping("/me")
    public ResponseEntity<?> me() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getName())) {
            return ResponseEntity.status(401).body("Not authenticated");
        }

        String username = auth.getName();
        Optional<User> optUser = userRepository.findByUsername(username);
        if (optUser.isEmpty()) return ResponseEntity.status(404).body("User not found");

        User user = optUser.get();

        Student student = null;
        try {
            student = studentRepository.findByUser(user).orElse(null);
        } catch (Exception ignore) {}

       
        Map<String, Object> response = new HashMap<>();
        response.put("user", user);
        if (student != null) {
            response.put("student", student);
        }

        return ResponseEntity.ok(response);
    }
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerJson(@RequestBody Map<String, Object> body) {
       
        String username = str(body.get("username"));
        String email = str(body.get("email"));
        String password = str(body.get("password"));
        String name = str(body.get("name"));
        String phone = str(body.get("phoneNumber"));
        if (phone == null) phone = str(body.get("phone"));
        String address = str(body.get("address"));
        Integer age = body.get("age") != null ? Integer.valueOf(body.get("age").toString()) : null;
        String gender = str(body.get("gender"));
        Long courseId = body.get("courseId") != null ? Long.valueOf(body.get("courseId").toString()) : null;

        if (username == null) username = email;
        if (username == null || password == null || name == null || email == null || courseId == null) {
            return ResponseEntity.badRequest().body("Required fields: username/email, password, name, email, courseId");
        }

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Invalid course ID"));

        try {
            
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setRole(Role.STUDENT);
            user = userService.registerUser(user);

           
            Student student = new Student();
            student.setName(name);
            student.setEmail(email);
            student.setPhoneNumber(phone);
            student.setAddress(address);
            student.setAge(age);
            student.setGender(gender == null ? "" : gender);
            student.setCourse(course);
            student.setUser(user);

            
            studentRepository.save(student);

            return ResponseEntity.ok(student);

        } catch (RuntimeException ex) {
         
            return ResponseEntity.status(409).body(Map.of("error", ex.getMessage()));
        }
    }


    // 
    @PostMapping(value = "/password-reset", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> resetPasswordJson(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String newPassword = body.get("newPassword");
        String confirmPassword = body.get("confirmPassword");
        if (username == null || newPassword == null || confirmPassword == null) {
            return ResponseEntity.badRequest().body("Required: username, newPassword, confirmPassword");
        }
        if (!newPassword.equals(confirmPassword)) {
            return ResponseEntity.badRequest().body("Passwords do not match");
        }
        Optional<User> opt = userRepository.findByUsername(username);
        if (opt.isEmpty()) return ResponseEntity.status(404).body("User not found");
        User user = opt.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return ResponseEntity.ok("Password reset successful");
    }

    private static String str(Object o) {
        return o == null ? null : o.toString();
    }
}
