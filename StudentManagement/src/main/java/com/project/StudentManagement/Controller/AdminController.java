package com.project.StudentManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.StudentManagement.Model.Course;
import com.project.StudentManagement.Model.Student;
import com.project.StudentManagement.Repository.StudentRepository;
import com.project.StudentManagement.Service.CourseService;
import com.project.StudentManagement.Service.StudentService;


@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping("/dashboard")
	public String showAdminDashboard(Model model) {
		long totalStudents = studentRepository.count();

        model.addAttribute("totalUsers", totalStudents);
	    return "admin-dashboard"; 
	}
	
	 @GetMapping("/courses")
	 public String getAllCourses(Model model) {
	     List<Course> courses = courseService.getAllCourses();
	     model.addAttribute("courses", courses);
	     return "course-list";
	}
	
	@GetMapping("/courses/add")
	public String addCourse() {
		return "add-course"; 
	}
	
	@PostMapping("/courses/add")
	public String saveCourse(@ModelAttribute Course course) {
		courseService.addCourse(course);
		return "redirect:/admin/courses";
	}
	
	@GetMapping("/courses/delete/{courseId}")
	public String deleteCourse(@PathVariable Long courseId) {
	    courseService.DeleteCourseById(courseId);
	    return "redirect:/admin/courses";
	}
	
	@GetMapping("/students/add")
	public String addStudent(Model model) {
	    model.addAttribute("student", new Student());
	    model.addAttribute("courses", courseService.getAllCourses());
	    return "add-student";
	}

	@PostMapping("/students/add")
	public String saveStudent(@ModelAttribute("student") Student student,
	                          @RequestParam("courseId") Long courseId) {
	    Course course = courseService.getCourseById(courseId); 
	    student.setCourse(course);
	    studentService.addStudent(student);
	    return "redirect:/admin/students";
	}
	
	@GetMapping("/students")
	public String getAllStudents(Model model) {
		model.addAttribute("students", studentService.getAllStudents());
		return "student-list";
	}
	
	@GetMapping("/students/edit/{id}")
	public String editStudent(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentService.getStudentById(id));
		model.addAttribute("courses", courseService.getAllCourses());
		model.addAttribute("isAdmin", true);
		return "edit-student";
	}
	
	@PostMapping("/students/edit/{id}")
	public String updateStudent(@PathVariable Long id,
	                            @ModelAttribute("student") Student student,
	                            @RequestParam("courseId") Long courseId) {
	    student.setId(id);
	    Course course = courseService.getCourseById(courseId); 
	    student.setCourse(course);
	    studentService.updateStudentById(id, student);
	    return "redirect:/admin/students";
	}
	@GetMapping("/students/delete/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return "redirect:/admin/students";
	}
	 @GetMapping("/students/search")
	    public String searchStudents(@RequestParam String keyword, Model model) {
	        List<Student> students = studentService.searchStudents(keyword);
	        model.addAttribute("students", students);
	        return "student-search-results";
	    }
}
