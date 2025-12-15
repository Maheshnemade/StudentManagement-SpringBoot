package com.project.StudentManagement.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Course {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(unique = true, nullable = false)
	    private String courseName;

	    @OneToMany(mappedBy = "course") 
	    @JsonManagedReference
	    private List<Student> students;

	    public Course() {}

	    public Course(Long id, String courseName) {
	        this.id = id;
	        this.courseName = courseName;
	    }
	    
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getCourseName() {
	        return courseName;
	    }

	    public void setCourseName(String courseName) {
	        this.courseName = courseName;
	    }

	    public List<Student> getStudents() {
	        return students;
	    }

	    public void setStudents(List<Student> students) {
	        this.students = students;
	    }
	
}
