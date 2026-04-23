package com.event.event_management.service;

import com.event.event_management.dto.*;
import com.event.event_management.entity.Role;
import com.event.event_management.entity.User;
import com.event.event_management.repository.UserRepository;
import com.event.event_management.security.JwtUtil;

import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    // 🔥 ✅ REGISTER (SECURED)
    public String register(RegisterRequest request) {

        // 🔥 Duplicate username check
        if (userRepository.existsByUsername(request.getUsername())) {
            return "Username already exists";
        }

        // 🔥 Duplicate email check
        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already exists";
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // 🔐 SECURITY: Never trust UI role
        if (request.getRole() != null && request.getRole() == Role.ADMIN) {
            throw new RuntimeException("Admin registration is not allowed");
        }
        
        if(request.getRole()!= null && request.getRole()== Role.MANAGER) {
        	user.setRole(Role.MANAGER);
        }

        // ✅ Force ORGANIZER role (based on your system design)
        user.setRole(Role.ORGANIZER);

        userRepository.save(user);

        return "User registered successfully";
    }

    // 🔐 ✅ LOGIN
    public AuthResponse login(LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String token = jwtUtil.generateToken(userDetails);

        return new AuthResponse(token);
    }

    // ✅ Fetch all organisers (for dropdown in UI)
    public List<User> getOrganizers() {
        return userRepository.findByRole(Role.ORGANIZER);
    }
}