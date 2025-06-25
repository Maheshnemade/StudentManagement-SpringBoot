package com.project.StudentManagement.Controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class RoleRedirectController {
	
	// This endpoint is hit immediately after successful login
    @GetMapping("/redirect-after-login")
    public void redirectBasedOnRole(Authentication authentication, HttpServletResponse response) throws IOException {
        if (authentication == null || !authentication.isAuthenticated()) {
            response.sendRedirect("/login?error=true");
            return;
        }
        // Loop through all roles assigned to the authenticated user
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            String role = authority.getAuthority();
            System.out.println("User Role: " + role);

            if ("ROLE_ADMIN".equals(role)) {
                response.sendRedirect("/admin/dashboard");
                return;
            } else if ("ROLE_STUDENT".equals(role)) {
                response.sendRedirect("/student/dashboard");
                return;
            }
        }
        
        //If no matching role is found, redirect to home page as fallback
        response.sendRedirect("/");
    }
}
