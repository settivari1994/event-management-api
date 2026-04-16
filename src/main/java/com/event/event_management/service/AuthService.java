package com.event.event_management.service;

import com.event.event_management.dto.*;
import com.event.event_management.entity.Role;
import com.event.event_management.entity.User;
import com.event.event_management.repository.UserRepository;
import com.event.event_management.security.JwtUtil;

import java.util.List;

import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    // ✅ REGISTER (UPDATED WITH EMAIL)
    public String register(RegisterRequest request) {

        // 🔥 duplicate username check
        if (userRepository.existsByUsername(request.getUsername())) {
            return "Username already exists";
        }

        // 🔥 duplicate email check
        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already exists";
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail()); // ✅ NEW
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        userRepository.save(user);

        return "User registered successfully";
    }

    // 🔐 LOGIN (NO CHANGE)
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
    
    public List<User> getOrganizers() {
        return userRepository.findByRole(Role.ORGANIZER);
    }
}