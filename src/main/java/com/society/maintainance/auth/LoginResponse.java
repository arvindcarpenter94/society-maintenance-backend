package com.society.maintenance.auth;

import com.society.maintenance.user.Role;

public class LoginResponse {

    private String token;
    private Role role;
    private Long flatId;
    
	public LoginResponse(String token, Role role, Long flatId) {
		super();
		this.token = token;
		this.role = role;
		this.flatId = flatId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Long getFlatId() {
		return flatId;
	}
	public void setFlatId(Long flatId) {
		this.flatId = flatId;
	}
    
    
}
