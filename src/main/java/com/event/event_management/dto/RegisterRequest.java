package com.event.event_management.dto;

import com.event.event_management.entity.Role;
import lombok.Data;

@Data
public class RegisterRequest {
	
	private String username;
    private String password;

    private Role role;
    
    public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

}