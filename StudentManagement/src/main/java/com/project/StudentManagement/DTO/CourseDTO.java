package com.project.StudentManagement.DTO;

public class CourseDTO {
	private Long id;
	private String courseName;

	public CourseDTO(Long id, String courseName) {
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
}
