package com.project.StudentManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.StudentManagement.Model.Course;
import com.project.StudentManagement.Service.CourseService;

@RestController
@RequestMapping("/public")
public class PublicCourseController { 

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public List<Course> getCourses() {
        return courseService.getAllCourses();
    }
    
   
}
