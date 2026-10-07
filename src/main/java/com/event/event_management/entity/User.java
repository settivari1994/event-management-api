package com.event.event_management.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Phone number - used as login username
    @Column(unique = true, nullable = false)
    private String username;

    // Password is never returned in JSON
    @JsonIgnore
    @Column(nullable = false)
    private String password;

    // User role
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Role role;

    // Display name of the user
    @Column(length = 100)
    private String name;

    // Email address
    @Column(unique = true, length = 150)
    private String email;

    // Mapping with Events (Organiser side)
    @ManyToMany(mappedBy = "organisers")
    @JsonBackReference
    private List<Event> events;


    // =========================================================
    // GETTERS
    // =========================================================

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Event> getEvents() {
        return events;
    }


    // =========================================================
    // SETTERS
    // =========================================================

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}

