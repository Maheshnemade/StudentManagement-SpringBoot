package com.project.StudentManagement.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.project.StudentManagement.Service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .userDetailsService(customUserDetailsService)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/", 
                    "/index", 
                    "/login", 
                    "/register",
                    "register/**",
                    "/password-reset", 
                    "/redirect-after-login", 
                    "/css/**", 
                    "/js/**", 
                    "/images/**", 
                    "/WEB-INF/views/**",
                    "/doLogin"
                ).permitAll()
                .requestMatchers("/WEB-INF/**").denyAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/student/**").hasRole("STUDENT")
                .requestMatchers("/students/edit/**").hasAnyRole("ADMIN", "STUDENT")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/doLogin")
                .defaultSuccessUrl("/redirect-after-login", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            .csrf(csrf -> csrf
                .ignoringRequestMatchers(
                    "/register", 
                    "/doLogin", 
                    "/logout",
                    "/password-reset", 
                    "/admin/**", 
                    "/student/**"
                    
                )
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
