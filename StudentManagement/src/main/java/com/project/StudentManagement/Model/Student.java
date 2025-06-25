package com.project.StudentManagement.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Student {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Size(min = 3, message = "Name must be at least 2 characters")
	private String name;
	@Email
	@Column(unique = true)
	private String email;
	@Column(unique = true)
	@Size(min = 10, max = 10, message = "Phone number must be 10 digits")
	private String phoneNumber;
	private String address;
	@Min(value = 18, message = "Age must be positive")
	private Integer age;
	
	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;
	
	
	@OneToOne
	@JoinColumn(name = "user_id", unique = true) 
	private User user;

	
	public Student() {
	}

	
	public Student(Long id, String name, String email, String phoneNumber, String address, Integer age, 
			Course course, User user) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.age = age;
		this.course = course;
		this.user = user;
	}

	

	public Integer getAge() {
		return age;
	}


	public void setAge(Integer age) {
		this.age = age;
	}


	public Course getCourse() {
		return course;
	}


	public void setCourse(Course course) {
		this.course = course;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
