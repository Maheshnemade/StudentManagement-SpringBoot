package com.project.StudentManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class StudentManagementApplication extends SpringBootServletInitializer {
	/**
	 * This method is used to configure the application when it is run as a WAR
	 * file. It sets the sources for the Spring Boot application.
	 *
	 * @param builder The SpringApplicationBuilder used to configure the
	 *                application.
	 * @return The configured SpringApplicationBuilder.
	 */	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(StudentManagementApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(StudentManagementApplication.class, args);
    }
}
